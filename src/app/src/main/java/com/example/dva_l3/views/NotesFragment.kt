/*
====================================================================================================

Auteurs : Nelson Jeanrenaud - Yohann Paulus - Luca Zacheo

Projet : Labo3 - Architecture MVVM, utilisation d’une base de données Room et d’un RecyclerView
Branche : DVA
Fichier : NoteFragments.kt

====================================================================================================
*/
package com.example.dva_l3.views

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dva_l3.Adapter
import com.example.dva_l3.MyApp
import com.example.dva_l3.R
import com.example.dva_l3.models.NoteAndSchedule
import com.example.dva_l3.viewModels.NoteViewModel
import com.example.dva_l3.viewModels.SortType


/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels{
        NoteViewModel.NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }
    private var currentSort: SortType? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.notes_RV)
        val adapter = Adapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        viewModel.types.observe(viewLifecycleOwner) {
            adapter.items = getAllSorted(adapter.items, it)
        }

        viewModel.allNotes.observe(viewLifecycleOwner) {
                list -> list?.let {
            adapter.updateList(it)
            }
        }

    }
    private fun getAllSorted(items: List<NoteAndSchedule>, created: SortType): List<NoteAndSchedule> {
        currentSort = created
        return when (created) {
            SortType.CREATED -> {
               items.sortedBy { it.note.creationDate }
            }
            SortType.ETA -> {
                items.sortedWith(compareBy(nullsLast()) { it.schedule?.date })
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }
}