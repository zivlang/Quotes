package com.example.seinfeldquotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.seinfeldquotes.model.Quote;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Quote> quotesList = this.getInt
        ent().getParcelableArrayListExtra("quotesList");
        Bundle listBundle = new Bundle();
        listBundle.putParcelableArrayList("quotesList", quotesList);

        Fragment listFragment = new ListFragment();
        listFragment.setArguments(listBundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragments_container, listFragment);

        ft.commit();

    }
}