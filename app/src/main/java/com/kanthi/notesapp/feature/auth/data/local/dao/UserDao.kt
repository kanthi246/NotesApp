package com.kanthi.notesapp.feature.auth.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kanthi.notesapp.feature.auth.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?
}
