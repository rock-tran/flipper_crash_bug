package co.core.toolbox

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import co.core.R
import co.core.fragments.NavigationManager

class AppNavigationManager(@IdRes private val frameId: Int,
                           private val fragmentManager: FragmentManager,
                           private val activity: Activity) : NavigationManager {

    override fun goBack(): Boolean {
        if (fragmentManager.backStackEntryCount == 0) {
            activity.finish()
            return false
        }

        fragmentManager.popBackStack()
        // Even popped back stack, the fragment which is added without addToBackStack would be showing.
        // So we need to remove it manually
        val transaction = fragmentManager.beginTransaction()
        val currentFrag = fragmentManager.findFragmentById(frameId)
        if (currentFrag != null) {
            transaction.remove(currentFrag)
            transaction.commit()
        }

        return true
    }

    override fun showPage(fragment: Fragment) {
        showPage(fragment, hasAnimation = true, isAddBackStack = true)
    }

    override fun showPage(fragment: Fragment, hasAnimation: Boolean, isAddBackStack: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        if (hasAnimation)
            transaction.setCustomAnimations(R.anim.fragment_enter,
                    R.anim.fragment_exit, R.anim.fragment_pop_enter,
                    R.anim.fragment_pop_exit)
        transaction.replace(frameId, fragment)
        val navigationState = NavigationState(frameId)
        if (isAddBackStack) {
            transaction.addToBackStack(navigationState.backStackName)
        }

        transaction.commit()
    }

    override val activePage: Fragment?
        get() = fragmentManager.findFragmentById(frameId)
}
