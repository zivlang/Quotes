package com.example.seinfeldquotes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.seinfeldquotes.model.Quote;

public class QuoteFragment extends Fragment {

    private Quote quote;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quote, container, false);

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
}