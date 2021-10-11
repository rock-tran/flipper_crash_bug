package com.project.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import co.core.dialog.NDialogFragment
import com.base.R

/**
 * Created by freesky1102 on 10/18/16.
 */

class LoadingDialogFragment : NDialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }
}
