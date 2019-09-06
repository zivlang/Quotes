package com.example.seinfeldquotes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.seinfeldquotes.model.Quote;

public class QuoteFragment extends Fragment {

    Quote quote;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quote, container, false);

        Context context = getActivity();

        quote = Quote.getCurrentQuote();

        TextView viewCharacter = rootView.findViewById(R.id.characterNameId);
        TextView viewSeason = rootView.findViewById(R.id.seasonId);
        TextView viewQuote = rootView.findViewById(R.id.quoteId);

        viewCharacter.setText(quote.getQuotedCharacter());
        viewSeason.setText(String.valueOf(quote.getSeason()));
        String quoteAndQuotationMarks = "\"" + quote.getQuote()+"\"";
        viewQuote.setText(quoteAndQuotationMarks);

        return rootView;
    }
    public void setQuote (Quote quote){
        this.quote = quote;
    }
}