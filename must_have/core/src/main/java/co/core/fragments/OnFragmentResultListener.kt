package co.core.fragments

import android.content.Intent

/**
 * Created by freesky1102 on 10/26/16.
 */
interface OnFragmentResultListener {

    fun onFragmentResult(requestCode: Int, action: Int, extraData: Intent?)

}
