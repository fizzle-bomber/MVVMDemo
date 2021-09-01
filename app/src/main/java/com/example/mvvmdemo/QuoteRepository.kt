package com.example.mvvmdemo

import android.content.Context
import androidx.lifecycle.LiveData

class QuoteRepository(private val quoteDao: QuoteDao) {

    var readAllData: LiveData<Quote>? = null
    fun getQuote(): LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

     suspend fun insertQuote(quote: Quote) {
         quoteDao.insertQuote(quote)
    }

    fun getLoginDetails(username: String, password:String) : LiveData<Quote>? {
        readAllData = quoteDao.readAllData(username,password)
        return readAllData
    }

    suspend fun deleteAll(){
        quoteDao.deleteAll()
    }
}