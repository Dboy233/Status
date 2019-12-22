package com.example.status

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var changeF = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, TestFragment1())
            .commit()
    }

    fun changeFragment(view: View) {
        if (changeF) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, TestFragment1())
                .commit()
            changeF = false
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TestFragment2())
                .commit()
            changeF = true
        }

    }

    fun changeActivity(view: View) {
        startActivity(Intent(this, MainActivity2::class.java))
    }


}
