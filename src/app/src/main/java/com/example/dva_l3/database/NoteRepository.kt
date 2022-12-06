package com.example.dva_l3.database

import androidx.lifecycle.LiveData
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Schedule
import com.example.dva_l3.viewModels.SortType

class NoteRepository(private val notesDao: NoteDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
    val allSchedules: LiveData<List<Schedule>> = notesDao.getAllSchedules()

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

    // on below line we are creating a delete method
    // for deleting our note from database.
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    // on below line we are creating a update method for
    // updating our note from database.
    fun getAllNotesSorted(created: SortType) {
        if (created == SortType.CREATED) {
            notesDao.getAllNotesSortedByCreate()
        } else {

        }
    }

    fun deleteAll() {
        notesDao.deleteAll()
    }
}