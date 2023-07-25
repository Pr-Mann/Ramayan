package com.micropineapplez.ramayan.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class AdFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, AdSecondActivity::class.java))
        finish()
    }
}