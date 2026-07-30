package com.kanthi.notesapp.feature.auth.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.sessionDataStore by preferencesDataStore(name = "session_prefs")

class SessionDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val keyUserId = longPreferencesKey("current_user_id")

    val currentUserId: Flow<Long?> = context.sessionDataStore.data.map { prefs -> prefs[keyUserId] }

    suspend fun setCurrentUserId(id: Long) {
        context.sessionDataStore.edit { prefs -> prefs[keyUserId] = id }
    }

    suspend fun clearSession() {
        context.sessionDataStore.edit { prefs -> prefs.remove(keyUserId) }
    }
}
