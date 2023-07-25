package com.micropineapplez.ramayan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.micropineapplez.ramayan.model.ChapterName
import com.micropineapplez.ramayan.room.RamayanDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterNameViewModel @Inject constructor(
    application: Application,
    private val ramayanDao: RamayanDao
) : AndroidViewModel(application) {

    private val _chapterNames = MutableLiveData<List<ChapterName>>()
    val chapterName: LiveData<List<ChapterName>>
        get() = _chapterNames

    fun getChapterNames() {
        viewModelScope.launch(Dispatchers.IO) {
            _chapterNames.postValue(ramayanDao.getChapterNames())
        }
    }
}
