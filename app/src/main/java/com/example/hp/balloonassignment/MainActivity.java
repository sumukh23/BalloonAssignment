package com.example.hp.balloonassignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView input;
    Button button;
    ProgressBar progressBar;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.input);
        button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        url = "https://www.bourncreative.com/how-to-design-a-website-header/";

        input.setText(url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAsynTask().execute();
            }
        });
    }

    private class MyAsynTask extends AsyncTask<Void, Void, Document> {

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Document doInBackground(Void... voids) {

            Document document = null;
            try{
                document = Jsoup.connect(url).get();
                document.getElementsByClass("site-inner").remove();
                document.getElementsByClass("footer-widgets").remove();
                document.getElementsByClass("site-footer").remove();
            } catch (IOException e){
                e.printStackTrace();
            }
            return document;
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);

            String temp = document.toString();

            Intent i = new Intent(MainActivity.this , SecondActivity.class);
            i.putExtra("url" , url);
            i.putExtra("document", temp);
            startActivity(i);
        }
    }
}
