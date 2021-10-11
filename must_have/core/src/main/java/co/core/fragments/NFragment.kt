package co.core.fragments

/**
 * @author TUNGDX
 */

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.core.NFragmentHost
import co.core.dialog.NDialogFragment
import co.core.dialog.OnActionInDialogListener
import co.core.imageloader.NImageLoader

/**
 * This is base fragment. <br></br>
 * It contains some default attributes: Context, Api, ImageLoader,
 * NavigationManager, Actionbar <br></br>
 */
abstract class NFragment : Fragment(), OnActionInDialogListener, OnFragmentResultListener {

    protected var mPageFragmentHost: NFragmentHost? = null
    private var mSaveInstanceStateCalled: Boolean = false
    protected var mImageLoader: NImageLoader? = null
    protected var mNavigationManager: NavigationManager? = null

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity !== mPageFragmentHost) {
            mPageFragmentHost = activity as NFragmentHost
            mImageLoader = mPageFragmentHost!!.imageLoader
            mNavigationManager = mPageFragmentHost!!.childNav
        }

        mSaveInstanceStateCalled = false
    }

    open protected val isHasActionbar: Boolean
        get() = true

    open protected val layoutRes: Int
        @LayoutRes
        get() = 0

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSaveInstanceStateCalled = false
    }

    /**
     * Shouldn't override this function...Use [.getLayoutRes]
     */
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mSaveInstanceStateCalled = false
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mSaveInstanceStateCalled = false
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mSaveInstanceStateCalled = false
    }

    /**
     * Method check state of fragment. Can not change state of fragment (like:
     * navigate in fragment, change layout...)

     * @return true Valid for change state, otherwise not valid
     */
    fun canChangeFragmentManagerState(): Boolean {
        val activity = activity
        return !(mSaveInstanceStateCalled || activity == null || activity.isFinishing)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mSaveInstanceStateCalled = true
    }

    override fun onDialogResult(dialog: NDialogFragment, requestCode: Int, action: Int, extraData: Intent?) {
        //TODO Implement the default action listener from dialog
    }

    override fun onFragmentResult(requestCode: Int, action: Int, extraData: Intent?) {
        //TODO Implement the default action listener from target fragment
    }
}
