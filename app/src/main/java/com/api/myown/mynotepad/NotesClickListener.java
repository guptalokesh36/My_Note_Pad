package com.api.myown.mynotepad;

import androidx.cardview.widget.CardView;

import com.api.myown.mynotepad.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
