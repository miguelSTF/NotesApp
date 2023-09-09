package com.mikeSTF.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikeSTF.notesapp.data.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}