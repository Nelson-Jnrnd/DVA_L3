package com.example.dva_l3

import android.app.Application
import com.example.dva_l3.database.NoteDatabase
import com.example.dva_l3.database.NoteRepository

class MyApp: Application() {
    val repository by lazy {
        val database = NoteDatabase.getDatabase(this)
        NoteRepository(database.getNotesDao())
    }
}