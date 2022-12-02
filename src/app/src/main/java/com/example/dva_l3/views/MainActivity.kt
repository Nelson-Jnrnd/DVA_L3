package com.example.dva_l3.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.fragment.app.FragmentContainerView
import com.example.dva_l3.R

class MainActivity : AppCompatActivity() {
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
                            true
                        }
                        R.id.sort_by_creation_date -> {
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
                            true
                        }
                        R.id.delete_all -> {
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
}