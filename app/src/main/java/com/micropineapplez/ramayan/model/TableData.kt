package com.micropineapplez.ramayan.model

import androidx.room.PrimaryKey

data class TableData(
    @PrimaryKey val _id: Int?,
    val Title: String?,
    val Detail: String?
)