package com.mikeSTF.notesapp.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.utils.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mikeSTF.notesapp.data.repositories.NotesRepository
import com.mikeSTF.notesapp.utils.AppBarState

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val application: Application,
    private val notesRepository: NotesRepository,
) : ViewModel() {

    private val _shouldShowSplashScreen = MutableStateFlow<Boolean>(true)
    val shouldShowSplashScreen = _shouldShowSplashScreen.asStateFlow()

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    var action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    private val _selectedNote: MutableStateFlow<Note?> = MutableStateFlow(null)
    val selectedNote: MutableStateFlow<Note?> = _selectedNote

    val searchAppBarState: MutableState<AppBarState> =
        mutableStateOf(AppBarState.CLOSED)

    private val _allNotes = MutableStateFlow<PagingData<Note>>(PagingData.empty())
    val allNotes: StateFlow<PagingData<Note>> = _allNotes

    init {
        viewModelScope.launch {
            delay(2000)
            _shouldShowSplashScreen.value = false
        }
        getAllNotes()
    }

    private fun getAllNotes() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                notesRepository.getAllNotes().cachedIn(viewModelScope).collect { pagingData ->
                    Log.d("Notes", pagingData.toString())
                    _allNotes.value = pagingData
                }
            }
        } catch (e: Exception) {
            Log.d("Notes", e.toString())
            _allNotes.value = PagingData.empty()
        }
    }

    fun getSelectedNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getSelectedNote(noteId = noteId).collect { note ->
                _selectedNote.value = note
            }
        }
    }

    fun validateNoteFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun updateNoteFields(selectedNote: Note?) {
        if (selectedNote != null) {
            id.value = selectedNote.id
            title.value = selectedNote.title
            description.value = selectedNote.description
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
        }
    }

    fun handleDatabaseAction(action: Action) {
        when (action) {
            Action.ADD -> {
                addNote()
            }
            else -> {
            }
        }
    }

    private fun addNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                title = title.value,
                description = description.value,
            )
            notesRepository.addNote(note)
        }
    }
}