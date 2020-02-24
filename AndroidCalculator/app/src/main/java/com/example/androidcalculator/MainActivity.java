package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] digits = new Button[10];
    String firstOperand = new String("");
    String secondOperand = new String("");
    Button clearBtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);
        clearBtn = findViewById(R.id.button_clear);

        initializeDigitButtons(digits);
        addClearFieldEvent(result);

        for (int i = 0; i < digits.length; ++i) {
            final int tempI = i;

            digits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String digit = digits[tempI].getText().toString();
                    String s = result.getText().toString();

                    result.setText(s+digit);

                }
            });
        }



    }


    private int getDigitButtonId(int digit){

        if(digit > 9 || digit < 0)
            return -1;

        int digitId = getResources()
                .getIdentifier("button"+digit, "id", getPackageName());

        return digitId;
    }

    private void initializeDigitButtons(Button[] digits) {

        for (int i = 0; i < digits.length; ++i) {

            int btnId = getDigitButtonId(i);
            digits[i] = findViewById(btnId);

        }
    }

    private void addClearFieldEvent(final TextView r){

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r.setText("");
            }
        });
    }


}
