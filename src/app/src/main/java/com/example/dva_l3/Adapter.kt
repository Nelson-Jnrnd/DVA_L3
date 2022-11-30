package com.example.dva_l3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (): RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val noteImg = itemView.findViewById<ImageView>(R.id.note_image)
        val noteTxtTitle = itemView.findViewById<TextView>(R.id.note_title)
        val noteTxtDescription = itemView.findViewById<TextView>(R.id.note_description)
        val noteImgClock = itemView.findViewById<ImageView>(R.id.note_image_clock)
        val noteTxtClock = itemView.findViewById<TextView>(R.id.note_text_clock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}