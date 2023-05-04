package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.withIndex

class FlowUseCase2ViewModel(
    stockPriceDataSource: StockPriceDataSource,
    defaultDispatcher: CoroutineDispatcher
) : BaseViewModel<UiState>() {

    /*

    Flow exercise 1 Goals
        1) only update stock list when Alphabet(Google) (stock.name ="Alphabet (Google)") stock price is > 2300$
        2) only show stocks of "United States" (stock.country == "United States")
        3) show the correct rank (stock.rank) within "United States", not the world wide rank
        4) filter out Apple  (stock.name ="Apple") and Microsoft (stock.name ="Microsoft"), so that Google is number one
        5) only show company if it is one of the biggest 10 companies of the "United States" (stock.rank <= 10)
        6) stop flow collection after 10 emissions from the dataSource
        7) log out the number of the current emission so that we can check if flow collection stops after exactly 10 emissions
        8) Perform all flow processing on a background thread

     */

    val currentStockPriceAsLiveData: LiveData<UiState> = stockPriceDataSource
        .latestStockList
        .withIndex()
        .onEach { indexValue ->
            println("indexValue: ${indexValue.index+1}")
        }
        .map {
            it.value
        }
        .take(10)
        .filter {
            val googlePrice = it.find { stock -> stock.name == "Alphabet (Google)" }?.currentPrice?: return@filter false
            googlePrice != null && googlePrice > 2300
        }.map {
            it.filter {
                it.country == "United States"
            }
        }.map {stockList->
           stockList.mapIndexed { index, it ->
               it.copy(rank = index + 1)
           }
        }.map {stockList->
            stockList.filter {
                it.name != "Apple" && it.name != "Microsoft"
            }
        }
        .map { stockList->
            stockList.filter {
                it.rank <= 10
            }
        }
        .map {
            UiState.Success(it) as UiState
        }.onStart {
            emit(UiState.Loading)
        }.asLiveData(defaultDispatcher)

}