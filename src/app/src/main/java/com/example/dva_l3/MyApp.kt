/*
====================================================================================================

Auteurs : Nelson Jeanrenaud - Yohann Paulus - Luca Zacheo

Projet : Labo3 - Architecture MVVM, utilisation d’une base de données Room et d’un RecyclerView
Branche : DVA
Fichier : MainApp.kt

====================================================================================================
*/
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