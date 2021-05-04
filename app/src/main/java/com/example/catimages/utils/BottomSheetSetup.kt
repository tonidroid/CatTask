package com.example.catimages.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import com.example.catimages.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


//height - bottomSheetHeight in percents
fun setupRatio(bottomSheetDialog: BottomSheetDialog, ctx: Context, height: Int) {
//    id = com.google.android.material.R.id.design_bottom_sheet for Material Components
//    id = android.support.design.R.id.design_bottom_sheet for support librares
    val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout?
    val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet as FrameLayout)
    val layoutParams = bottomSheet.layoutParams
    layoutParams.height = getBottomSheetDialogDefaultHeight(ctx, height)
    bottomSheet.layoutParams = layoutParams
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
}

fun getBottomSheetDialogDefaultHeight(context: Context, height: Int): Int {
    return getWindowHeight(context) * height / 100
}

fun getWindowHeight(context: Context): Int {
    // Calculate window height for fullscreen use
    val displayMetrics = DisplayMetrics()
    (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}