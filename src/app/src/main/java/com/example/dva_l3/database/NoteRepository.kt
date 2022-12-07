/*
====================================================================================================

Auteurs : Nelson Jeanrenaud - Yohann Paulus - Luca Zacheo

Projet : Labo3 - Architecture MVVM, utilisation d’une base de données Room et d’un RecyclerView
Branche : DVA
Fichier : NoteRepository.kt

====================================================================================================
*/
package com.example.dva_l3.database

import androidx.lifecycle.LiveData
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.NoteAndSchedule
import com.example.dva_l3.models.Schedule

class NoteRepository(private val notesDao: NoteDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<NoteAndSchedule>> = notesDao.getAllNotes()
    val nbNotes: LiveData<Int> = notesDao.getCount()


    // on below line we are creating an insert method
    // for adding the note to our database.
    fun insert(note: Note, schedule: Schedule?) {
        val id = notesDao.insert(note)
        print("ID: $id")
        if (schedule != null) {
            schedule.ownerId = id
            notesDao.insert(schedule)
        }
    }

    fun deleteAll() {
        notesDao.deleteAll()
        notesDao.deleteAllSchedule()
    }
}