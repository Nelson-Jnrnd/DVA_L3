package com.example.dva_l3.database

import androidx.lifecycle.LiveData
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Schedule
import com.example.dva_l3.viewModels.SortType

class NoteRepository(private val notesDao: NoteDao) {
    private var currentSort: SortType? = null
    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()
    val allSchedules: LiveData<List<Schedule>> = notesDao.getAllSchedules()
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