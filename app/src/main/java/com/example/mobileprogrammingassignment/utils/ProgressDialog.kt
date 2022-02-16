package com.example.mobileprogrammingassignment.utils

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import com.example.mobileprogrammingassignment.R

class ProgressDialog constructor(private val context: Context) {
    private val dialog = Dialog(context, R.style.TransparentProgressDialog)

    fun showProgress() {
        try {
            // check if progress dialog is already visible. If visible, then remove it.
            // hideProgress()
            val customDialog = dialog
            Log.e("MYTAG",customDialog.toString())
            customDialog.setCancelable(false)
            /*  customDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
              customDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)*/
            customDialog.setContentView(R.layout.progress_dialog)
            val lp = WindowManager.LayoutParams()
            val window = customDialog.window
            lp.copyFrom(window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = lp
            customDialog.show()
        } catch (e: Exception) {
            Log.e("MYTAG",e.printStackTrace().toString())
        }
    }

    fun hideProgress() {
        val customDialog = dialog
        try {
            if (customDialog.isShowing) {
                customDialog.dismiss()
            }
        } catch (e: Exception) {
            //Log.e("MYTAG",e.printStackTrace())
        }
    }
}