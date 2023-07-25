package com.micropineapplez.ramayan.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.micropineapplez.ramayan.model.ChapterName
import com.micropineapplez.ramayan.model.TableData

@Dao
interface RamayanDao {

    @Query("SELECT * FROM ChapterName")
    fun getChapterNames(): List<ChapterName>

    @RawQuery
    fun getTitleData(query: SupportSQLiteQuery): List<TableData>

    @RawQuery
    fun getDetailData(query: SupportSQLiteQuery): TableData
}