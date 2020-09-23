package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editUSD, editEUR, editVND;

    double usd2eur = 0.84;
    double usd2vnd = 23178.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUSD = findViewById(R.id.edit_usd);
        editEUR = findViewById(R.id.edit_eur);
        editVND = findViewById(R.id.edit_vnd);

        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUSD.setText("");
                editEUR.setText("");
                editVND.setText("");
            }
        });

        findViewById(R.id.button_convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (editUSD.hasFocus()) {
                        Log.v("TAG", "Convert USD");
                        double usd = Double.parseDouble(editUSD.getText().toString());
                        Log.v("TAG", "USD = " + usd);
                        double eur = usd * usd2eur;
                        double vnd = usd * usd2vnd;

                        editEUR.setText(String.format("%.2f", eur));
                        editVND.setText(String.format("%.0f", vnd));
                    } else if (editEUR.hasFocus()) {
                        Log.v("TAG", "Convert EUR");
                        double eur = Double.parseDouble(editEUR.getText().toString());
                        Log.v("TAG", "EUR = " + eur);
                        double usd = eur / usd2eur;
                        double vnd = usd * usd2vnd;

                        editUSD.setText(String.format("%.2f", usd));
                        editVND.setText(String.format("%.0f", vnd));
                    } else if (editVND.hasFocus()) {
                        Log.v("TAG", "Convert VND");
                        double vnd = Double.parseDouble(editVND.getText().toString());
                        Log.v("TAG", "VND = " + vnd);
                        double usd = vnd / usd2vnd;
                        double eur = usd * usd2eur;

                        editEUR.setText(String.format("%.2f", eur));
                        editUSD.setText(String.format("%.2f", usd));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}