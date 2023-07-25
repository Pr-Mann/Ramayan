package com.micropineapplez.ramayan.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.micropineapplez.ramayan.model.ChapterName

@Database(
    entities = [ChapterName::class],
    version = 1,
    exportSchema = false
)
abstract class RamayanDatabase : RoomDatabase() {
    abstract fun ramayanDao(): RamayanDao
}