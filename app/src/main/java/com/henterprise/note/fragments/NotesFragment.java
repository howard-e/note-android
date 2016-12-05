package com.henterprise.note.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.henterprise.note.R;
import com.henterprise.note.adapters.NotePictureAdapter;
import com.henterprise.note.models.Note;
import com.henterprise.note.utils.DummyNoteContent;
import com.henterprise.note.utils.Validator;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Howard.
 */

public class NotesFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.button_add_note)
    LinearLayout mAddNoteButton;

    private NotePictureAdapter notePictureAdapter;
    private ArrayList<Note> noteArrayList;
    private View createNoteView;

    DatabaseReference dummyDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        initCreateNoteDialog(inflater);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dummyDatabaseRef = database.getReference("notes").child("text").child("dummy_id");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        noteArrayList = DummyNoteContent.getNotes();
        notePictureAdapter = new NotePictureAdapter(getContext(), noteArrayList);
        mRecyclerView.setAdapter(notePictureAdapter);

        mAddNoteButton.setOnClickListener(this);

        return rootView;
    }

    private void initCreateNoteDialog(LayoutInflater inflater) {
        createNoteView = inflater.inflate(R.layout.fragment_create_note, null);
        MaterialSpinner spinner = (MaterialSpinner) createNoteView.findViewById(R.id.spinner);
        spinner.setItems("Text", "Picture", "Link", "Video");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Toast.makeText(getContext(), "Clicked: " + item, Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_note:
                if (createNoteView.getParent() != null)
                    ((ViewGroup) createNoteView.getParent()).removeView(createNoteView);

                new MaterialStyledDialog.Builder(getContext())
                        .setTitle("Add a Note!")
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .setHeaderColor(R.color.colorPrimary)
                        .setCustomView(createNoteView)
                        .setPositiveText("Submit")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                EditText titleEText = (EditText) createNoteView.findViewById(R.id.title_edit_text);
                                EditText descriptionEText = (EditText) createNoteView.findViewById(R.id.description_edit_text);

                                if (Validator.isEmpty(descriptionEText)) {
                                    // TODO: Notify user that description needs to be filled in
                                } else {
                                    dummyDatabaseRef.child("title").setValue(Validator.isEmpty(descriptionEText) ? null : titleEText.getText().toString());
                                    dummyDatabaseRef.child("description").setValue(descriptionEText.getText().toString());
                                }
                            }
                        })
                        .setNegativeText("Cancel")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.cancel();
                            }
                        })
                        .withIconAnimation(true)
                        .setScrollable(true)
                        .setCancelable(true)
                        .show();
                break;
        }
    }
}
