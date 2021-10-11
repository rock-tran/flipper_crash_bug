package com.project.dialog

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import co.core.dialog.NDialogFragment
import com.base.R

/**
 * Created by freesky1102 on 7/1/16.
 */
class AppDialogFragment : NDialogFragment() {

    class Builder : Parcelable {
        internal var positiveText: String? = null
        internal var negativeText: String? = null
        internal var message: String? = null
        internal var title: String? = null
        internal var isCancelable: Boolean = false

        constructor() {}

        /**
         * The positive button is used in case you want to create a dialog with one button.

         * @param positiveText text of positive button
         */
        fun setPositiveText(positiveText: String): Builder {
            this.positiveText = positiveText
            return this
        }

        fun setNegativeText(negativeText: String): Builder {
            this.negativeText = negativeText
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.isCancelable = cancelable
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun build(): AppDialogFragment {
            return AppDialogFragment.newInstance(this)
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.positiveText)
            dest.writeString(this.negativeText)
            dest.writeString(this.message)
            dest.writeString(this.title)
            dest.writeByte(if (this.isCancelable) 1.toByte() else 0.toByte())
        }

        protected constructor(input: Parcel) {
            this.positiveText = input.readString()
            this.negativeText = input.readString()
            this.message = input.readString()
            this.title = input.readString()
            this.isCancelable = input.readByte().toInt() != 0
        }

        companion object {

            @JvmField
            val CREATOR: Parcelable.Creator<Builder> = object : Parcelable.Creator<Builder> {
                override fun createFromParcel(source: Parcel): Builder {
                    return Builder(source)
                }

                override fun newArray(size: Int): Array<Builder?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    private lateinit var mBuilder: Builder
    private var mMessageView: TextView? = null
    private var mNegativeView: TextView? = null
    private var mPositiveView: TextView? = null
    private var mTitleView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
            getDataFrom(savedInstanceState)
        else
            getDataFrom(arguments!!)
    }

    override fun getDataFrom(bundle: Bundle) {
        super.getDataFrom(bundle)
        mBuilder = bundle.getParcelable(EXTRA_BUILDER)!!
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window ?: return

        window.setBackgroundDrawableResource(android.R.color.transparent)

        //set window size programmatically
        val displayMetrics = resources.displayMetrics
        val params = window.attributes

        val marginRL = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
        params.width = displayMetrics.widthPixels - 2 * marginRL
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window.setLayout(params.width, params.height)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val window = dialog?.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)

        val layoutRes: Int

        if (TextUtils.isEmpty(mBuilder.positiveText) || TextUtils.isEmpty(mBuilder.negativeText))
            layoutRes = R.layout.dialog_ok
        else
            layoutRes = R.layout.dialog_ok_cancel

        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMessageView = view.findViewById(R.id.content)
        mNegativeView = view.findViewById(R.id.btn_cancel)
        mPositiveView = view.findViewById(R.id.btn_ok)
        mTitleView = view.findViewById(R.id.title)

        displayText(mMessageView, mBuilder.message)
        displayText(mNegativeView, mBuilder.negativeText)
        displayText(mPositiveView, mBuilder.positiveText)
        displayText(mTitleView, mBuilder.title)

        settingOnClick(mNegativeView)
        settingOnClick(mPositiveView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCancelable = mBuilder.isCancelable
    }

    private val mOnClickListener = View.OnClickListener { v ->
        when (v.id) {

            R.id.btn_cancel -> notifyToHost(null, ACTION_CANCEL)

            R.id.btn_ok -> notifyToHost(null, ACTION_SET)
        }
    }

    private fun displayText(view: TextView?, text: String?) {
        if (view == null || TextUtils.isEmpty(text)) return
        view.text = text
    }

    private fun settingOnClick(view: View?) {
        if (view == null) return
        view.setOnClickListener(mOnClickListener)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_BUILDER, mBuilder)
    }

    companion object {

        private const val EXTRA_BUILDER = "extra_builder"

        private fun newInstance(builder: Builder): AppDialogFragment {
            val fragmentV4 = AppDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_BUILDER, builder)
            fragmentV4.arguments = bundle
            return fragmentV4
        }
    }
}
