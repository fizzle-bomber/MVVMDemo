package com.example.mvvmdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase :RoomDatabase(){
    abstract fun quoteDao():QuoteDao

    companion object{
        private var INSTANCE : QuoteDatabase? = null
        @InternalCoroutinesApi
        fun getDatabase(context: Context) :QuoteDatabase{
            if(INSTANCE == null){
                synchronized(this){
                INSTANCE = Room.databaseBuilder(context,
                QuoteDatabase::class.java,
                "quote_database")
                    //.createFromAsset("quotes.db")
                    .build()
                }
            }
            return INSTANCE!!
        }
    }
}