package com.mikeSTF.notesapp.di

import android.content.Context
import androidx.room.Room
import com.mikeSTF.notesapp.data.NotesDataBase
import com.mikeSTF.notesapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNotesDao(database: NotesDataBase) = database.notesDao()

}