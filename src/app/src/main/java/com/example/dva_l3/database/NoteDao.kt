/*
====================================================================================================

Auteurs : Nelson Jeanrenaud - Yohann Paulus - Luca Zacheo

Projet : Labo3 - Architecture MVVM, utilisation d’une base de données Room et d’un RecyclerView
Branche : DVA
Fichier : NoteDao.kt

====================================================================================================
*/
package com.example.dva_l3.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.NoteAndSchedule
import com.example.dva_l3.models.Schedule

@Dao
interface NoteDao {

    @Insert
    fun insert(note : Note) : Long

    @Insert
    fun insert(schedule: Schedule) : Long

    @Query("Select * from notesTable")
    fun getAllNotes(): LiveData<List<NoteAndSchedule>>

    @Query("DELETE FROM notesTable")
    fun deleteAll()

    @Query("DELETE FROM SchedulesTable")
    fun deleteAllSchedule()

    @Query("SELECT COUNT(*) FROM notesTable")
    fun getCount(): LiveData<Int>

}