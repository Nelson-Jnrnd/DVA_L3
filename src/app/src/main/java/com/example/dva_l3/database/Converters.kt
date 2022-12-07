/*
====================================================================================================

Auteurs : Nelson Jeanrenaud - Yohann Paulus - Luca Zacheo

Projet : Labo3 - Architecture MVVM, utilisation d’une base de données Room et d’un RecyclerView
Branche : DVA
Fichier : Converters.kt

====================================================================================================
*/
package com.example.dva_l3.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toCalendar(date: Long) =
        Calendar.getInstance().apply {
            time = Date(date)
        }

    @TypeConverter
    fun fromCalendar(date: Calendar) = date.time.time
}