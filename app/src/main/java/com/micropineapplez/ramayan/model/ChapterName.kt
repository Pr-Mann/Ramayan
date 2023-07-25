package com.micropineapplez.ramayan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChapterName(
    @PrimaryKey val _id: Int?,
    val ChapName: String?,
    val EngChapName: String?
)