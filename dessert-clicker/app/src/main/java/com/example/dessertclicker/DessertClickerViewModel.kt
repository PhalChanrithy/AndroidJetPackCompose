package com.example.dessertclicker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    public val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()
    private val desserts = Datasource.dessertList
    private var currentDessert: Dessert

    init {
        currentDessert = desserts.first()
        _uiState.value = DessertUiState(
            0,
            0,
            0,
            currentDessert.price,
            currentDessert.imageId
        )
    }

    fun updateDessert(revenue: Int, dessertSold: Int) {
        val revenue = uiState.value.revenue + currentDessert.price
        val dessertSold = uiState.value.dessertSold + 1
        currentDessert = determineDessertToShow(desserts, dessertSold)

        _uiState.update { currState ->
            currState.copy(
                revenue = revenue,
                dessertSold = dessertSold,
                currentDessertPrice = currentDessert.price,
                currentDessertImgId = currentDessert.imageId
            )
        }
    }

    fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    /**
     * Share desserts sold information using ACTION_SEND intent
     */
    fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertsSold, revenue)
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            ContextCompat.startActivity(intentContext, shareIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}