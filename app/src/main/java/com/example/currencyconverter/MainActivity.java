package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.currencyconverter.R.drawable.aus;
import static com.example.currencyconverter.R.drawable.can;
import static com.example.currencyconverter.R.drawable.eng;
import static com.example.currencyconverter.R.drawable.usa;

public class MainActivity extends AppCompatActivity {

    RadioGroup radGroup;
    RadioButton radBut;
    Button convertButton;
    TextView c1,c2,c3;
    TextView[] theRates = {c1,c2,c3};
    ImageView mainIV, c1IV, c2IV, c3IV;
    ImageView[] ratePics = {mainIV, c1IV, c2IV, c3IV};
    EditText yourMoney;
    double mainRate;

    Currency[] currency;

    public class Currency {
        public String[] country;
        public double value;
        int image;
        int pos;


        public Currency(String country, double value, int image, int pos) {
        }
    }

    public void changeCurrency (View view) {
        // get selected button
        int selectedCurrency = radGroup.getCheckedRadioButtonId();
        radBut = (RadioButton) findViewById(selectedCurrency);
        // match with currency
        int others = 1;
        for (int ii = 0; ii < currency.length; ii++) {
            if (radBut.getText().toString() == currency[ii].country.toString()) {
                currency[ii].pos = 0;
                mainIV.setImageResource(currency[ii].image);
                mainRate = currency[ii].value;
            }
            else {
                currency[ii].pos = others;
                ratePics[others - 1].setImageResource(currency[ii].image);
                others++;
            }
        }
        double temp;
        double dblYouMoney = Double.parseDouble(yourMoney.getText().toString());
        for (int ii = 0; ii < currency.length; ii++) {
            if (currency[ii].pos > 0) {
                temp = dblYouMoney / mainRate * currency[ii].value;
                c1.setText(String.valueOf(temp));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currency = new Currency[4];
        currency[0] = new Currency("AUS",0.7, aus, 0);
        currency[1] = new Currency("USA",1,usa, 1);
        currency[2] = new Currency("CAN",0.8,can, 2);
        currency[3] = new Currency("GBP",1.4, eng, 3);
        radGroup = findViewById(R.id.radGrp);
        mainIV = findViewById(R.id.mainImageView);
        c1IV = findViewById(R.id.c1ImageView);
        c2IV = findViewById(R.id.c2ImageView);
        c3IV = findViewById(R.id.c3ImageView);
        c1 = findViewById(R.id.c1TextView);
        c2 = findViewById(R.id.c2TextView);
        c3 = findViewById(R.id.c3TextView);
        yourMoney = findViewById(R.id.youMoneyEditText);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCurrency(view);
            }
        });
    }
}