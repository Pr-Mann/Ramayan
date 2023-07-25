package com.micropineapplez.ramayan.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.micropineapplez.ramayan.views.AdSecondActivityView

class AdSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdSecondActivityView(onStartClick = {
                startActivity(Intent(this, ChapterNameActivity::class.java))
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            })
        }
    }
}