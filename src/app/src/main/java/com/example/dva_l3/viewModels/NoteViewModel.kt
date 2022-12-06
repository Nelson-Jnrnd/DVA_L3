package com.example.dva_l3.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dva_l3.database.NoteDatabase
import com.example.dva_l3.database.NoteRepository
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class SortType {
    CREATED, ETA
}

class NoteViewModel (application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allNotes : LiveData<List<Note>>
    val allSchedules : LiveData<List<Schedule>>
    val repository : NoteRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
        allSchedules = repository.allSchedules
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun getAllNotesSorted(created: SortType) = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllNotesSorted(created)
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Note, generateRandomSchedule: Schedule?) = viewModelScope.launch(Dispatchers.IO) {
        if (generateRandomSchedule == null) {
            println("No schedule")
        }
        else {
            println("Schedule: ${generateRandomSchedule}")
        }
        repository.insert(note, generateRandomSchedule)

    }

    fun deleteNote() {
        repository.deleteAll()
    }
}