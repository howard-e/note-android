package com.howard.note.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.howard.note.R;
import com.howard.note.adapters.NotePictureAdapter;
import com.howard.note.utils.DummyNoteContent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class CurrentNotesFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        NotePictureAdapter notePictureAdapter = new NotePictureAdapter(getContext(), DummyNoteContent.getNotes());
        mRecyclerView.setAdapter(notePictureAdapter);

        return rootView;
    }
}
