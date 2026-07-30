package com.kanthi.notesapp.feature.notes.data.di

import com.kanthi.notesapp.feature.notes.data.repository.NotesRepositoryImpl
import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNotesRepository(impl: NotesRepositoryImpl): NotesRepository
}
