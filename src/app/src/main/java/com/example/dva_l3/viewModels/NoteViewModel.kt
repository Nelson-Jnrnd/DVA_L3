package com.example.dva_l3.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.dva_l3.database.NoteDatabase
import com.example.dva_l3.database.NoteRepository
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class SortType {
    CREATED, ETA
}

class NoteViewModel (private val repository: NoteRepository) : ViewModel() {
    // on below line we are creating a variable
    // for our all notes list and repository
    val allNotes : LiveData<List<Note>> = repository.allNotes
    val allSchedules : LiveData<List<Schedule>> = repository.allSchedules
    val nbNotes = repository.nbNotes


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Note, generateRandomSchedule: Schedule?) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note, generateRandomSchedule)
    }

    fun deleteNote() {
        repository.deleteAll()
    }

    fun getAllNotesSorted(created: SortType) {
        repository.getAllSorted(created)
}
class NotesViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}}