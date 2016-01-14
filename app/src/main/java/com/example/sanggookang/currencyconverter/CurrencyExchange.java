package com.example.sanggookang.currencyconverter;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrencyExchange extends AppCompatActivity {
    private static final double USD_TO_RMB = 6.57;
    private static final double USD_TO_EURO = 0.92;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_exchange);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void convert(View view) {
        String from = ((Spinner) findViewById(R.id.currency_from)).getSelectedItem().toString();
        String to = ((Spinner) findViewById(R.id.currency_to)).getSelectedItem().toString();
        double multiplier = 1;
        TextView input = (TextView) findViewById(R.id.input_amount);
        if(input.getText().length()==0){
            Toast.makeText(this, "Input is Invalid. Please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        double start = Double.parseDouble(((TextView) findViewById(R.id.input_amount)).getText().toString());
        NumberFormat formatter = new DecimalFormat("#0.00");
        if (from.equals(to)) {
            multiplier = 1;
        } else if (from.equals("USD")) {
            if (to.equals("RMB")) {
                multiplier = USD_TO_RMB;
            } else if (to.equals("EURO")) {
                multiplier = USD_TO_EURO;
            }
        } else if (from.equals("RMB")) {
            multiplier = 1 / USD_TO_RMB;
            if (to.equals("EURO")) {
                multiplier = multiplier * USD_TO_EURO;
            }
        } else if (from.equals("EURO")) {
            multiplier = 1 / USD_TO_EURO;
            if (to.equals("RMB")) {
                multiplier = multiplier * USD_TO_RMB;
            }
        }
        double result = start * multiplier;

        Toast.makeText(this, String.valueOf(formatter.format(result)), Toast.LENGTH_SHORT).show();
//        int id = view.getId();
//        ImageButton img = (ImageButton) findViewById(R.id.tmntbutton);
//        if (id == R.id.leoradiobutton) {
//            img.setImageResource(R.drawable.tmntleo);
//        } else if (id == R.id.mikeradiobutton) {
//            img.setImageResource(R.drawable.tmntmike);
//        } else if (id == R.id.donradiobutton) {
//            img.setImageResource(R.drawable.tmntdon);
//        } else {
//            img.setImageResource(R.drawable.tmntraph);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CurrencyExchange Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sanggookang.currencyconverter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CurrencyExchange Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sanggookang.currencyconverter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void flip(View view) {
        Spinner from = (Spinner) findViewById(R.id.currency_from);
        Spinner to = (Spinner) findViewById(R.id.currency_to);
        ArrayAdapter array = (ArrayAdapter)from.getAdapter();
        int from_position = array.getPosition(from.getSelectedItem());
        from.setSelection(array.getPosition(to.getSelectedItem()));
        to.setSelection(from_position);
    }
}
