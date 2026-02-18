package com.example.dessertclicker

import androidx.annotation.DrawableRes

data class DessertUiState(
    val revenue: Int = 0,
    val dessertSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = 0,
    @DrawableRes val currentDessertImgId: Int = 0
) {
}