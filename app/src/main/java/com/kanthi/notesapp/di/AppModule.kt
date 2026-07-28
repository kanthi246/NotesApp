package com.kanthi.notesapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//The three annotations you need to understand:
//@Module — marks the class as a recipe book for Hilt.
//@InstallIn(SingletonComponent::class) — decides the lifetime of what's provided. SingletonComponent = lives as long as the app.
//ViewModelComponent = lives as long as the ViewModel. Pick the right one and memory leaks become much harder to introduce accidentally.
//@Singleton — ensures Hilt only ever creates one instance of this thing. Without it, Hilt creates a new instance every time something asks for it.

@Module                              // "This class contains dependency recipes"
@InstallIn(SingletonComponent::class) // "These recipes live as long as the app does"
object AppModule {

    @Provides                        // "Here's how to create this type"
    @Singleton                       // "Create it once, reuse it everywhere"
    fun provideApplicationContext(
        @ApplicationContext context: Context  // Hilt already knows how to provide Context
    ): Context = context

    // We'll add provideNoteDatabase() and provideNoteRepository() here in Phase 2
}