package com.example.seinfeldquotes.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Quote implements Parcelable {

    public static final String TABLE_NAME = "movies";
    public static final String ID = "season";
    public static final String TITLE = "quote";
    public static final String IMAGE_URL = "quotedCharacter";
    public static final String RATING = "rating";
    public static final String RELEASE_YEAR = "releaseYear";
    public static final String GENRE = "genre";

    private static Quote currentQuote;

    private String quote;
    private int season;
    private String quotedCharacter;

    public Quote(String quoteText, int season, String quotedCharacter) {
        this.quote = quoteText;
        this.season = season;
        this.quotedCharacter = quotedCharacter;
    }

    public static void setCurrentQuote(Quote currentQuote) {
        Quote.currentQuote = currentQuote;
    }

    public static Quote getCurrentQuote() {
        return currentQuote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getSeason() {
        return season;
    }

    public void setQuotedCharacter(String quotedCharacter) {
        this.quotedCharacter = quotedCharacter;
    }

    public String getQuotedCharacter() {
        return quotedCharacter;
    }


    @Override
    public String toString() {
        return "Quote{" +
                "season=" + season +
                ", quote='" + quote + '\'' +
                ", quotedCharacter='" + quotedCharacter +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(season);
        dest.writeString(quote);
        dest.writeString(quotedCharacter);
    }

    public static final Creator<Quote> CREATOR = new Creator<Quote>() {
        public Quote createFromParcel(Parcel pc) {
            return new Quote(pc);
        }
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };

    private Quote(Parcel pc){
        season = pc.readInt();
        quote = pc.readString();
        quotedCharacter = pc.readString();
    }
}