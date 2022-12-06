package com.example.dva_l3.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Schedule

// annotation for dao class.
@Dao
interface NoteDao {

    // below is the insert method for
    // adding a new entry to our database.
    @Insert
    fun insert(note : Note) : Long

    @Insert
    fun insert(schedule: Schedule) : Long

    // below is the delete method
    // for deleting our note.
    @Delete
    suspend fun delete(note: Note)

    // below is the method to read all the notes
    // from our database we have specified the query for it.
    // inside the query we are arranging it in ascending
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data.
    @Query("Select * from notesTable order by noteId ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("Select * from schedulesTable order by scheduleId ASC")
    fun getAllSchedules(): LiveData<List<Schedule>>


    @Query("Select * from notesTable order by creationDate DESC")
    fun getAllNotesSortedByCreate(): LiveData<List<Note>>

    @Query("DELETE FROM notesTable")
    fun deleteAll()
}