package com.mikeSTF.notesapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikeSTF.notesapp.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
)
