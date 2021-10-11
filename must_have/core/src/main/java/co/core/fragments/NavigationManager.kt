package co.core.fragments

import androidx.fragment.app.Fragment
import co.core.actionbar.ActionbarBackHandler

/**
 * Created by freesky1102 on 5/27/16.
 */
interface NavigationManager {
    /**
     * Pop fragment in stack if stack isn't empty.

     * @return true if success, false otherwise. (maybe: stack is empty,
     * * activity is in onSaveInstance())
     */
    fun goBack(): Boolean

    val activePage: Fragment?

    fun showPage(fragment: Fragment)

    fun showPage(fragment: Fragment, hasAnimation: Boolean, isAddBackStack: Boolean)

    companion object {

        fun callBackHandlerInActivePage(navigationManager: NavigationManager): Boolean {

            val activePage = navigationManager.activePage

            var isHandled = false

            activePage?.let {
                if (it is ActionbarBackHandler) {
                    isHandled = (it as ActionbarBackHandler).onBackHandled()
                }

                if (!isHandled) {
                    isHandled = navigationManager.goBack()
                }
            }

            return isHandled
        }
    }
}
