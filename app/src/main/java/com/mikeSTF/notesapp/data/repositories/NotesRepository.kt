package com.mikeSTF.notesapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mikeSTF.notesapp.data.NotesDao
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.utils.Constants.NOTE_PAGE_SIZE
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class NotesRepository @Inject constructor(private val notesDao: NotesDao) {
    fun getAllNotes(): Flow<PagingData<Note>> {
        val pagingSourceFactory = { notesDao.getAllNotes() }
        return Pager(
            PagingConfig(
                pageSize = NOTE_PAGE_SIZE
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getSelectedNote(noteId: Int): Flow<Note> {
        return notesDao.getSelectedNote(noteId = noteId)
    }

    suspend fun addNote(note: Note) {
        notesDao.addNote(note = note)
    }
}