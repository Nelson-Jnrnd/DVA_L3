package com.example.dva_l3

import android.content.Context
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.dva_l3.models.*
import com.example.dva_l3.viewModels.SortType
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
    private val allSchedules = ArrayList<Schedule>()


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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        val note = allNotes[position]
        // find in NoteAndSchedule if the current note as a schedule
        val schedule = allSchedules.find { it.ownerId == note.noteId }

        println(allSchedules)

        holder.noteTxtTitle.text = note.title
        holder.noteTxtDescription.text = note.text

        when (note.type) {
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

        when (note.state) {
            State.IN_PROGRESS -> {
                holder.noteImgType.setColorFilter(Color.BLACK)
            }
            State.DONE -> {
                holder.noteImgType.setColorFilter(Color.GREEN)
            }
        }
        if (schedule != null) {
            println("schedule found for note ${note.noteId}")
            // Get the date as a string and set it to the text view
            val date = SimpleDateFormat("dd/MM/yyyy").format(schedule.date.time) // TODO change to locale or something
            holder.noteTxtClock.text = date.toString()
            holder.noteImgClock.visibility = View.VISIBLE
            holder.noteTxtClock.visibility = View.VISIBLE
        } else {
            // print to console
            println("No schedule found for note ${note.noteId}")
            holder.noteImgClock.visibility = View.GONE
            holder.noteTxtClock.visibility = View.GONE
        }

        /* TODO pk on ajoute une note quand on clique
        // on below line we are adding click listener
        // to our recycler view item.
        holder.itemView.setOnClickListener {
            // on below line we are calling a note click interface
            // and we are passing a position to it.
            noteClickInterface.onNoteClick(note, Note.generateRandomSchedule())
        } */

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

    fun updateScheduleList(newList: List<Schedule>) {
        println("Updated schedule list with ${newList.size} items")
        // on below line we are clearing
        // our notes array list
        allSchedules.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allSchedules.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface NoteClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onNoteClick(note: Note, generateRandomSchedule: Schedule?)
}

interface NoteDeleteInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onDeleteClick()
}
interface getAllNotesSorted {
    // creating a method for click action
    // on recycler view item for updating it.
    fun getAllNotesSorted(created: SortType)
}