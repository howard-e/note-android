package com.henterprise.note.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.henterprise.note.R;
import com.henterprise.note.fragments.ArchivedNotesFragment;
import com.henterprise.note.fragments.NotesFragment;
import com.henterprise.note.fragments.ReminderNotesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private int[] tabIcons = {
            R.drawable.ic_note_white_24dp,
            R.drawable.ic_alarm_white_24dp,
            R.drawable.ic_archive_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NotesFragment(), getString(R.string.string_notes));
        adapter.addFragment(new ReminderNotesFragment(), getString(R.string.string_reminders));
        adapter.addFragment(new ArchivedNotesFragment(), getString(R.string.string_archived_notes));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        // Simple non-custom tab title
        // mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        // mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        // mTabLayout.getTabAt(2).setIcon(tabIcons[2]);

        TextView tab1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab1.setText(getString(R.string.string_notes));
        tab1.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[0], 0, 0);
        mTabLayout.getTabAt(0).setCustomView(tab1);

        TextView tab2 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab2.setText(getString(R.string.string_reminders));
        tab2.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[1], 0, 0);
        mTabLayout.getTabAt(1).setCustomView(tab2);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab3.setText(getString(R.string.string_archived_notes));
        tab3.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[2], 0, 0);
        mTabLayout.getTabAt(2).setCustomView(tab3);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
