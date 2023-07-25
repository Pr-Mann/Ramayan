package com.micropineapplez.ramayan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.micropineapplez.ramayan.model.TableData
import com.micropineapplez.ramayan.room.RamayanDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val ramayanDao: RamayanDao
) : AndroidViewModel(application) {

    private val _tableData = MutableLiveData<List<TableData>>()
    val tableData: LiveData<List<TableData>>
        get() = _tableData

    private val title = savedStateHandle.get<String>("tableName")
    fun getTableName() = title

    private val chapName = savedStateHandle.get<String>("chapName")
    fun getChapName() = chapName

    fun getTitles() {
        viewModelScope.launch(Dispatchers.IO) {
            _tableData.postValue(ramayanDao.getTitleData(SimpleSQLiteQuery("SELECT _id, Title FROM $title")))
        }
    }
}
