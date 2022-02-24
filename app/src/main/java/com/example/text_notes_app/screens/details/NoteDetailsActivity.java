package com.example.text_notes_app.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.text_notes_app.App;
import com.example.text_notes_app.R;
import com.example.text_notes_app.model.Note;

public class NoteDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_NOTE = "NoteDetailsActivity.EXTRA_NOTE";

    private Note note;

    private EditText editText, editTitle;

    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        editText = findViewById(R.id.editText);
        editTitle = findViewById(R.id.editTitle);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.text);
            editTitle.setText(note.title);
        } else {
            note = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home: finish();
            break;

            case R.id.save:
                if (editText.getText().length()>0)
                {
                    note.text = editText.getText().toString();

                    if (editTitle.getText().length() < 0)
                    {
                        note.title = getString(R.string.note_title_hint);
                    }
                    else {
                        note.title = editTitle.getText().toString(); }

                    note.date = System.currentTimeMillis();

                    if (getIntent().hasExtra(EXTRA_NOTE))
                    {
                        App.getInstance().getNoteDao().update(note);
                    } else {App.getInstance().getNoteDao().insert(note);}

                    finish();
                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
