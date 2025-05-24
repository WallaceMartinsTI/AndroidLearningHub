package com.wcsm.androidlearninghub.guide_one_time_password

sealed interface OtpAction {
    data class OnEnterNumber(val number: Int?, val index: Int) : OtpAction
    data class OnChangeFieldFocus(val index: Int) : OtpAction
    data object OnKeyboardBack: OtpAction
}