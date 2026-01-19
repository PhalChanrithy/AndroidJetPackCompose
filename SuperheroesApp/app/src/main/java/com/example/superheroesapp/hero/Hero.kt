package com.example.superheroesapp.hero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @DrawableRes val heroImage: Int,
    @StringRes val name: Int,
    @StringRes val heroDescription: Int
) {
}