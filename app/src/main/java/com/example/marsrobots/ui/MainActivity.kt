package com.example.marsrobots.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marsrobots.R
import com.example.marsrobots.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}