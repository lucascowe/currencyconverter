package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public class Currency {
        public String country;
        public double value;
        public int image;
        public int pos;


        public Currency(String c, double v, int i, int p) {
            Log.i("Info","New Currency added " + c + " worth $" + v);
            country = c;
            value = v;
            image = i;
            pos = p;
        }
    }

    RadioGroup radGroup;
    RadioButton radBut;
    Button convertButton;
    TextView c1,c2,c3;
    TextView[] theRates = {c1,c2,c3};
    ImageView mainIV, c1IV, c2IV, c3IV;
    ImageView[] ratePics = {mainIV, c1IV, c2IV, c3IV};
    EditText yourMoney;
    double mainRate;

    Currency[] currency = new Currency[4];

    public void convert (View view) {
        // get selected button
        int selectedCurrency = radGroup.getCheckedRadioButtonId();
        radBut = findViewById(selectedCurrency);
        // match with currency
        int others = 1;
        for (int ii = 0; ii < 4; ii++) {
            Log.i("Check","radBut " + radBut.getText() + " vs " + currency[ii].country);
            if (radBut.getText().toString().equals(currency[ii].country)) {
                Log.i("Progress", "Main country " + currency[ii].country);
                currency[ii].pos = 0;
                ratePics[0].setImageResource(currency[ii].image);
                Log.i("Progress", "Main image is  " + currency[ii].image);
                mainRate = currency[ii].value;
                Log.i("Progress", "Main rate is  " + mainRate);
            }
            else {
                currency[ii].pos = others;
                Log.i("Other", "Pos " + currency[ii].pos + " Country " +currency[ii].country + " image " + currency[ii].image);
                ratePics[others].setImageResource(currency[ii].image);
                others++;
            }
        }
        double temp;
        double dblYouMoney = Double.parseDouble(yourMoney.getText().toString());
        for (int ii = 0; ii < currency.length; ii++) {
            if (currency[ii].pos > 0) {
                temp = dblYouMoney / mainRate * currency[ii].value;
                theRates[currency[ii].pos-1].setText(String.valueOf(temp));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currency[0] = new Currency("AUS",0.7, R.drawable.aus, 0);
        Log.i("Info", "currency[0] created for " + currency[0].country);
        currency[1] = new Currency("USA",1, R.drawable.usa, 1);
        currency[2] = new Currency("CAN",0.8, R.drawable.can, 2);
        currency[3] = new Currency("ENG",1.4, R.drawable.eng, 3);
        radGroup = findViewById(R.id.radGrp);
        convertButton = findViewById(R.id.convertBnt);
        ratePics[0] = findViewById(R.id.mainImageView);
        ratePics[1] = findViewById(R.id.c1ImageView);
        ratePics[2] = findViewById(R.id.c2ImageView);
        ratePics[3] = findViewById(R.id.c3ImageView);
        theRates[0] = findViewById(R.id.c1TextView);
        theRates[1] = findViewById(R.id.c2TextView);
        theRates[2] = findViewById(R.id.c3TextView);
        yourMoney = findViewById(R.id.youMoneyEditText);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert(view);
            }
        });
    }
}