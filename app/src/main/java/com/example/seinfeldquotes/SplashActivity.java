package com.example.seinfeldquotes;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seinfeldquotes.model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class SplashActivity extends AppCompatActivity {

    AsyncTask<String, String, String> getJSONAndParseIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.seinfeld_theme_song);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getJSONAndParseIt = new GetJSONAndParseIt().execute();
            }
        }, 2000);
    }

    public class GetJSONAndParseIt extends AsyncTask<String,String,String>{

        ArrayList<Quote> quotesList;

        @Override
        protected String doInBackground(String... strings) {

            String jsonString = null;
            try {
                jsonString = getJSONFromURL();
            } catch (Exception e) {
                e.printStackTrace();
            }

            quotesList = parseJSON(jsonString);
            toMainActivity(quotesList);
            return null;
        }

        private String getJSONFromURL() throws Exception {

            //connecting the web page from which data will be read
            String downloadUrl = "https://seinfeld-quotes.herokuapp.com/quotes";
            HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(downloadUrl).openConnection();
            //an object that reads from the internet
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder fullJSON = new StringBuilder(); // a string that will hold the JSON
            String line; // will hold a certain line from the JSON
            while ((line = bufferedReader.readLine()) != null) { //unless the read line is null,
                // it's being saved in line
                fullJSON.append(line); // adding the read line to the already saved string
            }
            //Close our InputStream and Buffered reader
            bufferedReader.close();
            String responseTxt = fullJSON.toString();
            Log.d(TAG, "doInBackground: responseText " + responseTxt);

            return responseTxt;
        }

        private ArrayList<Quote> parseJSON(String jsonString) {

            quotesList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray quotesArray = jsonObject.getJSONArray("quotes");

                for (int i = 0; i < quotesArray.length(); i++) {

                    JSONObject quoteObject = quotesArray.getJSONObject(i);

                    String quoteText = quoteObject.getString("quote");
                    String quotedCharacter = quoteObject.getString("author");
                    int season = quoteObject.getInt("season");

                    Quote quote = new Quote(quoteText,season,quotedCharacter);
                    quotesList.add(quote);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return quotesList;
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }

    private void toMainActivity(ArrayList<Quote> quotesList) {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putParcelableArrayListExtra("quotesList", quotesList);

        Log.d("Monitoring", quotesList.toString());

        startActivity(intent);
        finish();
    }
}