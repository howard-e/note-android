package com.henterprise.note.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.henterprise.note.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class ArchivedNotesFragment extends Fragment {

    @BindView(R.id.button_add_note)
    LinearLayout mAddNoteButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, rootView);

        mAddNoteButton.setVisibility(View.GONE);

        return rootView;
    }
}
