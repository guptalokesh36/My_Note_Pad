package com.api.myown.mynotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.api.myown.mynotepad.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText edittext_notes, edittext_title;
    ImageView imageview_save;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageview_save = findViewById(R.id.imageview_save);
        edittext_notes = findViewById(R.id.edittext_notes);
        edittext_title = findViewById(R.id.edittext_title);

        notes = new Notes();

        try {
            notes= (Notes) getIntent().getSerializableExtra("old_note");
            edittext_title.setText(notes.getTitle());
            edittext_notes.setText(notes.getNotes());
            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        imageview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edittext_title.getText().toString();
                String description = edittext_notes.getText().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "please write something", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE d MM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote){
                    notes = new Notes();
                }


                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}