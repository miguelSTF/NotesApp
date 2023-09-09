package com.mikeSTF.notesapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mikeSTF.notesapp.data.models.Note
import com.mikeSTF.notesapp.utils.Constants.DATABASE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM $DATABASE_TABLE ORDER BY id ASC")
    fun getAllNotes(): PagingSource<Int, Note>

    @Query("SELECT * FROM $DATABASE_TABLE WHERE id=:noteId")
    fun getSelectedNote(noteId: Int): Flow<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

}