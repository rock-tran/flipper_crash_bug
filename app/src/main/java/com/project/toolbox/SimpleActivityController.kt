package com.project.toolbox

import co.core.activities.NActivity
import co.core.fragments.NavigationManager
import co.core.imageloader.NImageLoader
import co.core.toolbox.AppNavigationManager
import com.base.fragments.AppFragmentHost
import com.project.MyApplication

/**
 * Created by freesky1102 on 8/14/16.
 */
class SimpleActivityController(activity: NActivity) : AppFragmentHost {

    private var mImageLoader: NImageLoader? = null

    private var mNavigationManager: AppNavigationManager

    override val imageLoader: NImageLoader
        get() = mImageLoader!!

    override val childNav: NavigationManager
        get() = mNavigationManager

    init {
        mImageLoader = MyApplication.get().imageLoader
        mNavigationManager = AppNavigationManager(android.R.id.content,
                activity.supportFragmentManager, activity)
    }

    fun doOnDestroy() {
        mImageLoader = null
    }

    fun doOnBackPress(): Boolean {
        return callBackHandlerOnActivePage()
    }

    private fun callBackHandlerOnActivePage(): Boolean {
        return NavigationManager.callBackHandlerInActivePage(mNavigationManager)
    }
}
