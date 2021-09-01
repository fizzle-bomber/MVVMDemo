package com.example.mvvmdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.example.mvvmdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val Dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(Dao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


//            mainViewModel.getQuotes().observe(this ,{
//                binding.quotes = it.toString()
//            })
        binding.btnAddQuote.setOnClickListener {
            val name = binding.textInputUser.text.toString()
            val passWord = binding.textInputPassword.text.toString()

            mainViewModel.getLoginDetails(name , passWord)?.observe(this, Observer {
                if (it == null) {
                    Toast.makeText(this, "User Not found", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                }
            })
//            val quote = Quote(0 , "This is testing" , "Sanjay")
//            mainViewModel.insertQuote(quote)
        }

        binding.btnDeleteAll.setOnClickListener{
            mainViewModel.deleteAll()
        }

        binding.btnInsertUser.setOnClickListener{
            val name = binding.regEmail.text.toString()
            val password = binding.regPass.text.toString()
            val quote = Quote(0 , name , password)
            mainViewModel.insertQuote(quote)
        }
    }
}