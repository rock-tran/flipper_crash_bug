package co.core.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import co.core.NNavigationManagerHost
import co.core.actionbar.ActionbarBackHandler
import co.core.dialog.NDialogFragment
import co.core.dialog.OnActionInDialogListener
import co.core.fragments.NavigationManager
import co.core.fragments.OnFragmentResultListener
import co.core.toolbox.extensions.createInstance
import co.core.toolbox.extensions.getGenericTypeClass
import co.core.toolbox.extensions.getLastGenericTypeClass
import co.core.toolbox.extensions.showToast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Rock on 5/27/2018.
 */
abstract class BaseMVVMFragment<VM : BaseViewModel,
        VMF : ViewModelProvider.Factory,
        B : ViewBinding> : Fragment(),
    ActionbarBackHandler, OnActionInDialogListener, OnFragmentResultListener {

    protected var binding: B? = null

    @Inject
    internal lateinit var vmf: VMF

    protected lateinit var viewModel: VM

    private val mHostNav: NavigationManager? by lazy {

        var navHost: NNavigationManagerHost? = null

        parentFragment?.let {
            if (it is NNavigationManagerHost) navHost = it
        }

        if (navHost == null) {
            if (activity is NNavigationManagerHost) navHost = activity as NNavigationManagerHost
        }

        navHost?.childNav
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = savedInstanceState ?: arguments
        bundle?.let { extractData(it) }
        setUpViewModel()?.invoke(viewModel)
    }

    protected open fun doObserver(): (VM.() -> Unit)? {
        //In sub class, you will need to override this.
        return null
    }

    protected open fun setUpViewModel(): (VM.() -> Unit)? {
        //In sub class, you will need to override this.
        return null
    }

    protected open fun extractData(bundle: Bundle) {
        //Override in sub class
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val bindingClass = this::class.getLastGenericTypeClass().kotlin as KClass<B>
        if (bindingClass != ViewBinding::class) {
            val tempBinding = bindingClass.createInstance(inflater, container, false)
            binding = tempBinding
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (::viewModel.isInitialized) {
            viewModel.loadingLV.observe(viewLifecycleOwner, Observer {
                when (it) {
                    Status.LOADING -> showLoading()
                    Status.SUCCESS -> hideLoading()
                    null, Status.ERROR -> hideLoading()
                }
            })

            viewModel.errorLV.observe(viewLifecycleOwner, Observer {
                showToast(it.message)
            })

            viewModel.messageLV.observe(viewLifecycleOwner, Observer { showToast(it) })

            doObserver()?.invoke(viewModel)
        }

        if (setupViews != null && binding != null) {
            setupViews!!.invoke(binding!!)
        }
    }

    protected open val setupViews: (B.() -> Unit)? = null

    override fun onBackHandled(): Boolean {
        return false
    }

    override fun onDialogResult(
        dialog: NDialogFragment,
        requestCode: Int,
        action: Int,
        extraData: Intent?
    ) {
        // Implement in sub-class
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val vmClass = this::class.getGenericTypeClass(0).kotlin as KClass<VM>

        if (vmClass != BaseViewModel::class) {
            AndroidSupportInjection.inject(this)
        }

        if (::vmf.isInitialized) {
            viewModel = ViewModelProvider(this, vmf).get(vmClass.java)
        }
    }

    fun showLoading() {

    }

    fun hideLoading() {

    }

    fun showDialog(dialogFragment: NDialogFragment) {
        dialogFragment.show(childFragmentManager, DIALOG_TAG)
    }

    fun showDialog(requestCode: Int, dialogFragment: NDialogFragment) {
        val arguments = dialogFragment.arguments ?: Bundle()
        NDialogFragment.putRequestCode(arguments, requestCode)
        dialogFragment.arguments = arguments
        dialogFragment.show(childFragmentManager, DIALOG_TAG)
    }

    fun <T> observeLV(liveData: LiveData<T>, observer: Observer<T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    companion object {

        private const val DIALOG_TAG = "dialog_tag"

        /**
         * just back screen
         */
        const val ACTION_CANCEL = 0

        /**
         * return a value back to the previous screen
         */
        const val ACTION_SET = -1

        fun <T : Fragment> createInstance(oClass: Class<T>, initClosure: Bundle.() -> Unit): T {
            val fragment = oClass.newInstance()
            val bundle = Bundle()
            initClosure.invoke(bundle)
            fragment.arguments = bundle
            return fragment
        }
    }

    fun sendToNav(action: (nav: NavigationManager) -> Unit) {
        mHostNav?.let { action.invoke(it) }
    }

    override fun onFragmentResult(requestCode: Int, action: Int, extraData: Intent?) {
        //Rock: Implement in subclass the action from target fragment
    }

    fun showFragmentForResult(requestCode: Int, fragment: Fragment) {
        fragment.setTargetFragment(this, requestCode)
        sendToNav { nav -> nav.showPage(fragment) }
    }

    fun setFragmentResult(action: Int, intent: Intent?) {
        val requestCode = targetRequestCode
        val targetFrag = targetFragment
        if (targetFrag == null || targetFrag !is OnFragmentResultListener) return
        targetFrag.onFragmentResult(requestCode, action, intent)
    }

    fun requireBinding() = binding!!
}