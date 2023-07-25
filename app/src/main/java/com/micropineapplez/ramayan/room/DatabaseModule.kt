package com.micropineapplez.ramayan.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RamayanDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RamayanDatabase::class.java,
            "ramayandb"
        ).createFromAsset("database/ramayandb.db").build()
    }

    @Provides
    fun provideUserDao(ramayanDatabase: RamayanDatabase): RamayanDao {
        return ramayanDatabase.ramayanDao()
    }
}