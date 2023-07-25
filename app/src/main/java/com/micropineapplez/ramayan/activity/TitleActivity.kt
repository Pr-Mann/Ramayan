package com.micropineapplez.ramayan.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.micropineapplez.ramayan.views.TitleActivityView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TitleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TitleActivityView()
        }
    }
}