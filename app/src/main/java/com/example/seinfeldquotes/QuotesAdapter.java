package com.example.seinfeldquotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seinfeldquotes.model.Quote;

import java.util.ArrayList;
import java.util.List;

class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private Context context;
    private List<Quote> quotesList;

    public QuotesAdapter(Context context) {

        this.context = context;
        quotesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_list, parent,false);

        return new ViewHolder(itemView);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView viewSeason, viewCharacter;

        LinearLayout quoteEntry;

        ViewHolder(View view) {

            super(view);
            viewSeason = view.findViewById(R.id.rowSeasonId);
            viewCharacter = view.findViewById(R.id.rowCharacterId);
            quoteEntry = view.findViewById(R.id.listRowId);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Quote currentQuote = quotesList.get(position);

        holder.viewSeason.setText(String.valueOf(quotesList.get(position).getSeason()));
        holder.viewCharacter.setText(quotesList.get(position).getQuotedCharacter());
        holder.quoteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toQuoteFragment(v);
            }

            private void toQuoteFragment(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                Quote.setCurrentQuote(currentQuote);

                Fragment quoteFragment = new QuoteFragment();

//        Log.d("Monitoring", "Going to QuoteFragment");

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragments_container, quoteFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public void attachList(ArrayList<Quote> quotesList) {
        this.quotesList = quotesList;
    }
}
