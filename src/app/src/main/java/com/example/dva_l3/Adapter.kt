package com.example.dva_l3

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.State
import com.example.dva_l3.models.Type
import com.example.dva_l3.views.MainActivity


class Adapter(
    val context: Context,
    val noteClickInterface: NoteClickInterface,
    val noteDeleteInterface: MainActivity
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    // on below line we are creating a
    // variable for our all notes list.
    private val allNotes = ArrayList<Note>()

    // on below line we are creating a view holder class.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val noteImgType = itemView.findViewById<ImageView>(R.id.note_image)
        val noteTxtTitle = itemView.findViewById<TextView>(R.id.note_title)
        val noteTxtDescription = itemView.findViewById<TextView>(R.id.note_description)
        val noteImgClock = itemView.findViewById<ImageView>(R.id.note_image_clock)
        val noteTxtClock = itemView.findViewById<TextView>(R.id.note_text_clock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file for each item of recycler view.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        holder.noteTxtTitle.text = allNotes[position].title
        holder.noteTxtDescription.text = allNotes[position].text

        when (allNotes[position].type) {
            Type.NONE -> {
                holder.noteImgType.setImageResource(R.drawable.note)
            }
            Type.TODO-> {
                holder.noteImgType.setImageResource(R.drawable.todo)
            }
            Type.SHOPPING-> {
                holder.noteImgType.setImageResource(R.drawable.shopping)
            }
            Type.WORK-> {
                holder.noteImgType.setImageResource(R.drawable.work)
            }
            Type.FAMILY-> {
                holder.noteImgType.setImageResource(R.drawable.family)
            }
        }

        when (allNotes[position].state) {
            State.IN_PROGRESS -> {
                holder.noteImgType.setColorFilter(Color.BLACK)
            }
            State.DONE -> {
                holder.noteImgType.setColorFilter(Color.GREEN)
            }
        }

        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            noteClickInterface.onNoteClick(allNotes[position])
        }

    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return allNotes.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<Note>) {
        // on below line we are clearing
        // our notes array list
        allNotes.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allNotes.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface NoteClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onNoteClick(note: Note)
}

interface NoteDeleteInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onDeleteClick()
}
interface getAllNotesSorted {
    // creating a method for click action
    // on recycler view item for updating it.
    fun getAllNotesSorted()
}