package com.example.sqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        db = SQLiteHelper(this)
        binding.btSubmit.setOnClickListener {
            var firstName = binding.etFirst.text.toString()
            var lastName = binding.etLast.text.toString()
            var userName = binding.etUser.text.toString()
            if (db.insertData(firstName, lastName, userName))
            {
                Toast.makeText(this, "Record store", Toast.LENGTH_LONG).show()
            } else
            {
                Toast.makeText(this, "Record does not store", Toast.LENGTH_LONG).show()

            }
        }
    }
}









