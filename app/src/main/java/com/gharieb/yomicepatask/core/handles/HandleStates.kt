package com.gharieb.yomicepatask.core.handles

import android.content.Context
import com.gharieb.yomicepatask.core.common.dialogs.alertDialogProgressBar
import com.gharieb.yomicepatask.core.common.dialogs.okDialog
import com.gharieb.yomicepatask.core.common.dialogs.progressBarDialog

object HandleStates {
    fun handleErrorResult(errorMessage: String?,context: Context) {
        alertDialogProgressBar?.dismiss()
        okDialog(message = errorMessage, context = context)
    }
   fun handleLoadingState(context: Context) {
        progressBarDialog(context = context)
    }
}