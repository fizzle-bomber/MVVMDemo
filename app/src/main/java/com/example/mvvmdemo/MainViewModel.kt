package com.example.mvvmdemo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository):ViewModel() {

    var readAllData: LiveData<Quote>? = null
    fun getQuotes(): LiveData<List<Quote>>{
        return quoteRepository.getQuote()
    }
    fun insertQuote(quote : Quote){
        viewModelScope.launch {
            quoteRepository.insertQuote(quote)
        }
    }

    fun getLoginDetails( username: String, password:String) : LiveData<Quote>? {
        readAllData = quoteRepository.getLoginDetails(username,password)
        return readAllData
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.deleteAll()
        }
    }
}