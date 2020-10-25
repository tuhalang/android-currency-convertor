package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] currency = { "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "HKD", "NZD"};
    double[] toVNDs = { 23177, 27493.72, 221.36, 30235.53, 16546.06, 17649.95, 25634.2, 3466.09, 2990.5, 15461.23};

    Spinner from, to;
    EditText amount;
    TextView result;

    double toVND = toVNDs[0], fromVND= toVNDs[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = (Spinner) findViewById(R.id.from);
        to = (Spinner) findViewById(R.id.to);
        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);

        ArrayAdapter<String> aac = new ArrayAdapter(this,android.R.layout.simple_spinner_item,currency);
        aac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        from.setOnItemSelectedListener(this);
        from.setAdapter(aac);

        to.setOnItemSelectedListener(this);
        to.setAdapter(aac);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                exchange();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setText("");
                result.setText("");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spin = (Spinner)adapterView;

        if(spin.getId() == R.id.from)
        {
            toVND = toVNDs[i];
        }else if(spin.getId() == R.id.to)
        {
            fromVND = 1/toVNDs[i];
        }

        exchange();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void exchange(){
        String amountString = amount.getText().toString();
        if(!amountString.equals("")) {
            double amountFrom = Double.parseDouble(amountString);
            double amountResult = amountFrom * fromVND * toVND;

            result.setText(String.valueOf(amountResult));
        }else{
            result.setText("");
        }
    }
}