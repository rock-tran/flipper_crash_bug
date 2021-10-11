package co.core.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import co.core.NNavigationManagerHost
import co.core.dialog.NDialogFragment
import co.core.dialog.OnActionInDialogListener
import co.core.toolbox.AppNavigationManager
import co.core.toolbox.extensions.createInstance
import co.core.toolbox.extensions.getGenericTypeClass
import co.core.toolbox.extensions.getLastGenericTypeClass
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseMVVMActivity<VM : BaseViewModel,
        VMF : ViewModelProvider.Factory,
        B : ViewBinding> : AppCompatActivity(),
    NNavigationManagerHost, OnActionInDialogListener {

    protected var binding: B? = null

    @Inject
    internal lateinit var vmf: VMF

    protected lateinit var viewModel: VM

    override val childNav by lazy {
        AppNavigationManager(android.R.id.content, supportFragmentManager, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingClass = this::class.getLastGenericTypeClass().kotlin as KClass<B>
        if (bindingClass != ViewBinding::class) {
            val inflater = LayoutInflater.from(this)
            val tempBinding = bindingClass.createInstance(inflater)
            binding = tempBinding
            setContentView(tempBinding.root)
        }

        val vmClass = this::class.getGenericTypeClass(0).kotlin as KClass<VM>

        if (vmClass != BaseViewModel::class) {
            AndroidInjection.inject(this)
        }

        if (::vmf.isInitialized) {
            viewModel = ViewModelProvider(this, vmf).get(vmClass.java)
        }

        if (::viewModel.isInitialized) {
            viewModel.loadingLV.observe(this, Observer {
                it ?: return@Observer
                when (it) {
                    Status.LOADING -> showLoading()
                    Status.SUCCESS -> hideLoading()
                    Status.ERROR -> hideLoading()
                }
            })

            viewModel.errorLV.observe(this) {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }

            viewModel.messageLV.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

            doObserver()?.invoke(viewModel)
        }
    }

    protected open fun doObserver(): (VM.() -> Unit)? {
        //In sub class, you will need to override this.
        return null
    }

    override fun onDialogResult(
        dialog: NDialogFragment,
        requestCode: Int,
        action: Int,
        extraData: Intent?
    ) {
        //Rock: Implement the default action listener from dialog
    }

    fun showLoading() {

    }

    fun hideLoading() {

    }

    fun showDialog(dialogFragment: NDialogFragment) {
        dialogFragment.show(supportFragmentManager, DIALOG_TAG)
    }

    fun showDialog(requestCode: Int, dialogFragment: NDialogFragment) {
        val arguments = dialogFragment.arguments ?: Bundle()
        NDialogFragment.putRequestCode(arguments, requestCode)
        dialogFragment.arguments = arguments
        dialogFragment.show(supportFragmentManager, DIALOG_TAG)
    }

    protected fun <T> observeLV(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(this, observer)
    }

    fun withBinding(closure: B.() -> Unit) {
        closure.invoke(binding!!)
    }

    fun requireBinding() = binding!!

    companion object {

        private const val DIALOG_TAG = "dialog_tag"

    }
}


