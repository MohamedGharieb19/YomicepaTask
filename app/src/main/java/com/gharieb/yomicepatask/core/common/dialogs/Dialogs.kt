package com.gharieb.yomicepatask.core.common.dialogs

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.gharieb.yomicepatask.databinding.ConfirmCancelDialogBinding
import com.gharieb.yomicepatask.databinding.OkDialogBinding
import com.gharieb.yomicepatask.databinding.ProgressBarBinding

// Progress bar dialog
var alertDialogProgressBar: AlertDialog? = null
fun progressBarDialog(context: Context) {
    if (alertDialogProgressBar == null) {
        val binding = ProgressBarBinding.inflate(
            LayoutInflater.from(
                context
            ), null, false
        )

        alertDialogProgressBar = AlertDialog.Builder(context).create()
        alertDialogProgressBar?.setView(binding.root)
        alertDialogProgressBar?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialogProgressBar?.show()
        alertDialogProgressBar?.setCanceledOnTouchOutside(false)

        alertDialogProgressBar?.setOnDismissListener {
            alertDialogProgressBar = null
        }
    }
}

// Ok dialog
var alertDialogOk: AlertDialog? = null
fun okDialog(message: String?, context: Context) {
    if (alertDialogOk == null) {
        val binding = OkDialogBinding.inflate(
            LayoutInflater.from(
                context
            ), null, false
        )

        alertDialogOk = AlertDialog.Builder(context).create()
        alertDialogOk?.setView(binding.root)
        alertDialogOk?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialogOk?.show()
        alertDialogOk?.setCanceledOnTouchOutside(true)

        binding.apply {
            textMessage.text = message ?: ""

            button.setOnClickListener {
                alertDialogOk!!.dismiss()
            }
        }

        alertDialogOk?.setOnDismissListener {
            alertDialogOk = null
        }
    }
}

//// wifi dialog
//var alertDialogWifi: AlertDialog? = null
//fun wifiDialog(context: Context) {
//    if (alertDialogWifi == null) {
//        val binding = WifiDialogBinding.inflate(
//            LayoutInflater.from(
//                context
//            ), null, false
//        )
//
//        alertDialogWifi = AlertDialog.Builder(context).create()
//        alertDialogWifi?.setView(binding.root)
//        alertDialogWifi?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        alertDialogWifi?.show()
//        alertDialogWifi?.setCanceledOnTouchOutside(false)
//
//        alertDialogWifi?.setOnDismissListener {
//            alertDialogWifi = null
//        }
//    }
//}

// update app dialog
var alertDialogConfirmCancel: AlertDialog? = null
fun confirmCancelDialog(message: String?, context: Context, callback: () -> Unit) {
    if (alertDialogConfirmCancel == null) {
        val binding = ConfirmCancelDialogBinding.inflate(
            LayoutInflater.from(
                context
            ), null, false
        )

        alertDialogConfirmCancel = AlertDialog.Builder(context).create()
        alertDialogConfirmCancel?.setView(binding.root)
        alertDialogConfirmCancel?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialogConfirmCancel?.show()
        alertDialogConfirmCancel?.setCanceledOnTouchOutside(false)

        binding.apply {
            text.text = message ?: ""

            confirmButton.apply {
                setOnClickListener {
                    callback()
                    alertDialogConfirmCancel?.dismiss()
                }
            }

            cancelButton.apply {
                setOnClickListener {
                    alertDialogConfirmCancel?.dismiss()
                }
            }
        }

        alertDialogConfirmCancel?.setOnDismissListener {
            alertDialogConfirmCancel = null
        }
    }
}
