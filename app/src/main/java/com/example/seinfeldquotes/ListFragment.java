package com.example.seinfeldquotes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seinfeldquotes.model.Quote;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        Log.d(TAG, "in ListFragment");

        Context context = getActivity();

        ArrayList<Quote> quotesList;
        QuotesAdapter quotesAdapter;
        RecyclerView recyclerView;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        quotesAdapter = new QuotesAdapter(context);

        recyclerView = rootView.findViewById(R.id.RVId);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration;
        if(context != null) {
            dividerItemDecoration = new DividerItemDecoration(context, linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }

        recyclerView.setAdapter(quotesAdapter);

        Bundle listBundle = getArguments();
        if(listBundle != null){
            quotesList = getArguments().getParcelableArrayList("quotesList");
            quotesAdapter.attachList(quotesList);
        }
        return rootView;
    }
}
