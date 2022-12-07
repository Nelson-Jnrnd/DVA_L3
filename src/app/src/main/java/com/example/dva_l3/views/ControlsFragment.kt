package com.example.dva_l3.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.dva_l3.MyApp
import com.example.dva_l3.R
import com.example.dva_l3.models.Note
import com.example.dva_l3.viewModels.NoteViewModel




class ControlsFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels{
        NoteViewModel.NotesViewModelFactory((requireActivity().application as MyApp).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val generate = view.findViewById<Button>(R.id.generate_button)
        val delete = view.findViewById<Button>(R.id.delete_button)
        val cpt = view.findViewById<TextView>(R.id.nb_notes)

        viewModel.nbNotes.observe(viewLifecycleOwner) {
            cpt.text = it.toString()
        }

        generate.setOnClickListener {
            viewModel.addNote(Note.generateRandomNote(), Note.generateRandomSchedule(),)
        }

        delete.setOnClickListener {
            viewModel.deleteNote()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_controls, container, false)
    }

}