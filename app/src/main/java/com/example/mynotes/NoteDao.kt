package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note : Note)


    /*This gives us data from our app, but we want our activity know, if any kind of changes are done, so
    * we make it Live Data*/
    @Query(value = "Select * from notes_table order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>
}