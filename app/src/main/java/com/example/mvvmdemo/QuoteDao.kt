package com.example.mvvmdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote:Quote)

    @Query("SELECT * FROM quote WHERE text LIKE :name AND author LIKE :password")
    fun readAllData(name: String, password: String): LiveData<Quote>

    @Query("DELETE FROM quote")
    suspend fun deleteAll()


}