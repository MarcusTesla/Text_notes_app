package com.example.text_notes_app.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.text_notes_app.App;
import com.example.text_notes_app.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData()
    {
        return noteLiveData;
    }
}
