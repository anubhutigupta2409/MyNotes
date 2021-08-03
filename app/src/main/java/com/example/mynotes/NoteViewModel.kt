package com.example.mynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application)
{
        val allNotes : LiveData<List<Note>>
        private val repository : NoteRepository

        //initialisation
        init {
            val dao = NoteDataBase.getDatabase(application).getNoteDao()
            repository = NoteRepository(dao)
            allNotes = repository.allNotes
        }

        fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)//we made this viewmodelscope as delete function is a suspend function, so we had to make this a background task, using coroutine here
        }

        fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }

}