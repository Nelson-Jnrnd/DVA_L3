package com.example.dva_l3

import android.annotation.SuppressLint
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
import com.example.dva_l3.views.NotesFragment
import java.util.*
import java.util.concurrent.TimeUnit


class Adapter(
    val context: NotesFragment
) :

    RecyclerView.Adapter<Adapter.ViewHolder>() {


    // on below line we are creating a
    // variable for our all notes list.
    private val allNotes = ArrayList<NoteAndSchedule>()
    private val allSchedules = ArrayList<Schedule>()
    var items = listOf<NoteAndSchedule>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // on below line we are setting data to item of recycler view.
        val notes = items[position]
        val note = notes.note
        // find in NoteAndSchedule if the current note as a schedule
        val schedule = allSchedules.find { it.ownerId == note.noteId }

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
            // Get the date as a string and set it to the text view
            val date = SimpleDateFormat("dd/MM/yyyy").format(schedule.date.time)
            val current = SimpleDateFormat("dd/MM/yyyy").format(Date())
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val firstDate: Date = sdf.parse(date)
            val secondDate: Date = sdf.parse(current)


            if (firstDate < secondDate) {
                holder.noteTxtClock.setTextColor(Color.RED)
                holder.noteTxtClock.text = "Late"
                holder.noteImgClock.setColorFilter(Color.RED)
            }
            else if (firstDate == secondDate) {
                holder.noteTxtClock.setTextColor(Color.YELLOW)
                holder.noteTxtClock.text = "Today"
                holder.noteImgClock.setColorFilter(Color.YELLOW)
            }
            else{
                val diff: Long = firstDate.time - secondDate.time
                val day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
                if (day >= 30){
                    val month = Math.abs(day / 30)
                    if (month >= 12){
                        val year = Math.abs(month / 12)
                        holder.noteTxtClock.text = "$year years"
                    }
                    else{
                        holder.noteTxtClock.text = "$month months"
                    }
                }
                else{
                    holder.noteTxtClock.text = "$day days"
                }
            }

            holder.noteImgClock.visibility = View.VISIBLE
            holder.noteTxtClock.visibility = View.VISIBLE
        } else {
            holder.noteImgClock.visibility = View.GONE
            holder.noteTxtClock.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return items.size
    }

    // below method is use to update our list of notes.
    fun updateList(newList: List<NoteAndSchedule>) {
        // on below line we are clearing
        // our notes array list
        allNotes.clear()
        items = allNotes
        // on below line we are adding a
        // new list to our all notes list.
        allNotes.addAll(newList)
        items = allNotes

        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }

    fun updateScheduleList(newList: List<Schedule>) {
        // on below line we are clearing
        // our notes array list
        allSchedules.clear()
        items = allNotes
        // on below line we are adding a
        // new list to our all notes list.
        allSchedules.addAll(newList)
        items = allNotes
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}
