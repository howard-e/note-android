package com.howard.note.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.howard.note.R;
import com.howard.note.adapters.NotePictureAdapter;
import com.howard.note.models.Note;
import com.howard.note.utils.DummyNoteContent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class CurrentNotesFragment extends Fragment implements SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private NotePictureAdapter notePictureAdapter;
    private ArrayList<Note> noteArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        noteArrayList = DummyNoteContent.getNotes();
        notePictureAdapter = new NotePictureAdapter(getContext(), noteArrayList);
        mRecyclerView.setAdapter(notePictureAdapter);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                notePictureAdapter.animateTo(noteArrayList);
                mRecyclerView.scrollToPosition(0);
                return true; // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true; // Return true to expand action view
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // TODO: Investigate why without the following line to pull back the full list of content, it was affecting the global arraylist of notes
        noteArrayList = DummyNoteContent.getNotes();
        final ArrayList<Note> filteredModelList = filter(noteArrayList, newText);
        notePictureAdapter.animateTo(filteredModelList);
        mRecyclerView.scrollToPosition(0);
        return true;
    }

    private ArrayList<Note> filter(ArrayList<Note> models, String query) {
        query = query.toLowerCase();

        final ArrayList<Note> filteredModelList = new ArrayList<>();
        for (Note model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
