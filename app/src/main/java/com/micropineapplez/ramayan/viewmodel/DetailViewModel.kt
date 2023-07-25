package com.micropineapplez.ramayan.viewmodel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val ramayanDao: RamayanDao
) : AndroidViewModel(application) {

    private val _tableData = MutableLiveData<TableData>()

    private val _tableDetail = MutableLiveData<String>()
    val tableDetail: LiveData<String>
        get() = _tableDetail

    private val title = savedStateHandle.get<String>("title")
    fun getTitle() = title

    private val chapName = savedStateHandle.get<String>("chapName")
    fun getChapName() = chapName

    private val tableName = savedStateHandle.get<String>("tableName")
    private val id = savedStateHandle.get<String>("id")

    fun getDetails() {
        viewModelScope.launch {
            val ioDeferred = async(Dispatchers.IO) {
                ramayanDao.getDetailData(SimpleSQLiteQuery("SELECT _id, Detail FROM $tableName WHERE _id = $id"))
            }
            val defaultDeferred = async(Dispatchers.Default) {
                val tableData = ioDeferred.await()
                _tableData.postValue(tableData)
                annotatedString(tableData.Detail.toString())
            }
            val tableDetail = defaultDeferred.await()
            _tableDetail.postValue(tableDetail)
        }
    }

    private fun annotatedString(string: String): String {
        var detailString = string
        val replacements = mapOf(
            "भावार्थ:-" to "<font color='${Color.Red.toArgb()}'><br>भावार्थ:-</font>",
            "भावार्थ:" to "<font color='${Color.Red.toArgb()}'><br>भावार्थ:</font>",
            "भावार्थ :" to "<font color='${Color.Red.toArgb()}'><br>भावार्थ:</font>",
            "\n" to "<br>",
            "सोरठा:" to "<font color='${Color.Red.toArgb()}'>सोरठा:</font>",
            "सोरठा :" to "<font color='${Color.Red.toArgb()}'>सोरठा:</font>",
            "चौपाई:" to "<font color='${Color.Red.toArgb()}'>चौपाई:</font>",
            "चौपाई :" to "<font color='${Color.Red.toArgb()}'>चौपाई:</font>",
            "श्लोक:" to "<font color='${Color.Red.toArgb()}'>श्लोक:</font>",
            "श्लोक :" to "<font color='${Color.Red.toArgb()}'>श्लोक:</font>",
            "दोहा:" to "<font color='${Color.Red.toArgb()}'>दोहा:</font>",
            "दोहा :" to "<font color='${Color.Red.toArgb()}'>दोहा:</font>",
            "छन्द:" to "<font color='${Color.Red.toArgb()}'>छन्द:</font>",
            "छन्द :" to "<font color='${Color.Red.toArgb()}'>छन्द:</font>"
        )

        for ((oldValue, newValue) in replacements) {
            detailString = detailString.replace(oldValue, newValue)
        }
        return detailString
    }
}
