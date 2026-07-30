package com.kanthi.notesapp.feature.auth.data.mapper

import com.kanthi.notesapp.feature.auth.data.local.entity.UserEntity
import com.kanthi.notesapp.feature.auth.domain.model.User

fun UserEntity.toDomain(): User = User(id = id, name = name, email = email)
