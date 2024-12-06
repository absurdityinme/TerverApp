package com.example.terverapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) { init() }
    }

    private fun init() {
//            supportFragmentManager.beginTransaction()
//                .add(mainContainerId, ViewPagerFragment())
//                .commit()
    }

        companion object {
            val mainContainerId: Int = R.id.main_container
        }
}