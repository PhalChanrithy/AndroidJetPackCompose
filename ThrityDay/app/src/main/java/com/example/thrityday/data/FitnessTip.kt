package com.example.thrityday.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FitnessTip(
    val number: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val title: Int,
    @StringRes val descriptRes: Int
) {
}