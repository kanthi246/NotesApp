package com.kanthi.notesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp  // This triggers Hilt's code generation for the entire app
class AppApplication : Application()