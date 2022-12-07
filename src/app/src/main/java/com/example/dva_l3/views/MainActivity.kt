package com.example.dva_l3.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.dva_l3.*
import com.example.dva_l3.database.NoteDatabase
import com.example.dva_l3.database.NoteRepository
import com.example.dva_l3.models.Note
import com.example.dva_l3.models.Note.Companion.generateRandomNote
import com.example.dva_l3.models.Note.Companion.generateRandomSchedule
import com.example.dva_l3.models.Schedule
import com.example.dva_l3.viewModels.NoteViewModel
import com.example.dva_l3.viewModels.NotesViewModelFactory
import com.example.dva_l3.viewModels.SortType


class MainActivity : AppCompatActivity(), NoteClickInterface, NoteDeleteInterface{

    val repository by lazy {
        val database = NoteDatabase.getDatabase(this)
        NoteRepository(database.getNotesDao())
    }

    private val viewModel: NoteViewModel by viewModels{
        NotesViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Labo 3"

        if (findViewById<FragmentContainerView>(R.id.fragment_container_notes) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_notes, NotesFragment())
                .commit()
        }
        if (findViewById<FragmentContainerView>(R.id.fragment_container_controls) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_controls, ControlsFragment())
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.sort -> {
                val popup = PopupMenu(this, findViewById(R.id.sort))
                popup.inflate(R.menu.sort)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.sort_by_ETA -> {
                            viewModel.getAllNotesSorted(SortType.ETA)
                            true
                        }
                        R.id.sort_by_creation_date -> {
                            viewModel.getAllNotesSorted(SortType.CREATED)
                            Toast.makeText(this, "wesh... ", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
                true
            }
            R.id.add -> {
                val popup = PopupMenu(this, findViewById(R.id.add))
                popup.inflate(R.menu.actions)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.generate -> {
                            onNoteClick(generateRandomNote(),generateRandomSchedule())
                            Toast.makeText(this, "Generated Note ", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id.delete_all -> {
                            onDeleteClick()
                            Toast.makeText(this, "All notes deleted ", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    override fun onNoteClick(note: Note, generateRandomSchedule: Schedule?) {
        viewModel.addNote(note, generateRandomSchedule)
    }
    override fun onDeleteClick() {
        viewModel.deleteNote()
    }
}