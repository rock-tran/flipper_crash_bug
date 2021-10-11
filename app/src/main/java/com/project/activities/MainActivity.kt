package com.project.activities

import android.os.Bundle
import co.core.activities.NActivity
import co.core.fragments.NavigationManager
import co.core.imageloader.NImageLoader
import com.base.R
import com.base.fragments.AppFragmentHost
import com.project.screens.DemoMVVMFragment
import com.project.toolbox.SimpleActivityController

class MainActivity : NActivity(), AppFragmentHost {

    private lateinit var mDelegate: SimpleActivityController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDelegate = SimpleActivityController(this)

        val navigationManager = mDelegate.childNav
        val currentFragment = navigationManager.activePage
        if (currentFragment == null) {
            navigationManager.showPage(DemoMVVMFragment(), false, false)
        }
    }

    override val imageLoader: NImageLoader
        get() = mDelegate.imageLoader

    override val childNav: NavigationManager
        get() = mDelegate.childNav

    override fun onDestroy() {
        super.onDestroy()
        mDelegate.doOnDestroy()
    }

    override fun onBackPressed() {
        if (!mDelegate.doOnBackPress())
            super.onBackPressed()
    }
}
