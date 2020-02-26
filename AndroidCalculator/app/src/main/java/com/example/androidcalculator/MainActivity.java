package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] digits = new Button[10];
    Button[] operators = new Button[4];
    Button clearBtn, equalsBtn;
    TextView result, tempResult;
    String firstOperand = "";
    String secondOperand = "";
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        result = findViewById(R.id.textViewResult);
        tempResult = findViewById(R.id.textViewTemp);
        clearBtn = findViewById(R.id.button_clear);
        equalsBtn = findViewById(R.id.button_equals);
        initializeDigitButtons();
        initializeOperatorButtons();

        //Event listeners
        addClearFieldsEvent();
        addDigitEntryEvent();
        addOperatorEvent();
        addDisplayResultEvent();
    }


    private int getDigitButtonId(int digit) {

        if (digit > 9 || digit < 0)
            return -1;

        int digitId = getResources()
                .getIdentifier("button" + digit, "id", getPackageName());

        return digitId;
    }

    private void initializeDigitButtons() {

        for (int i = 0; i < digits.length; ++i) {

            int btnId = getDigitButtonId(i);
            digits[i] = findViewById(btnId);

        }
    }

    private void initializeOperatorButtons() {

        for (int i = 0; i < operators.length; ++i) {

            int index = i + 10;
            int opId = getResources()
                    .getIdentifier("button" + index, "id", getPackageName());

            operators[i] = findViewById(opId);

        }
    }

    private void addDigitEntryEvent() {

        for (int i = 0; i < digits.length; ++i) {
            final int tempI = i;

            digits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String digit = digits[tempI].getText().toString();
                    String s = result.getText().toString();
                    String num = s + digit;
                    result.setText(num);

                }
            });
        }
    }

    private void addOperatorEvent() {

        for (int i = 0; i < operators.length; ++i) {
            final int tempI = i;

            operators[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    operator = operators[tempI].getText().toString();
                    firstOperand = result.getText().toString();

                    if(!firstOperand.isEmpty()) {
                        tempResult.setText(firstOperand);
                        result.setText("");
                    }
                }
            });

        }
    }

    private void addClearFieldsEvent() {

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText("");
                tempResult.setText("");
            }
        });
    }

    private void addDisplayResultEvent(){

        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstOperand = tempResult.getText().toString();
                secondOperand = result.getText().toString();

                if (!(firstOperand.isEmpty()) && !(secondOperand.isEmpty())) {

                    int a = Integer.parseInt(firstOperand);
                    int b = Integer.parseInt(secondOperand);
                    int c = 0;

                    switch (operator) {

                        case "+":
                            c = a + b;
                            break;

                        case "-":
                            c = a - b;
                            break;

                        case "*":
                            c = a * b;
                            break;

                        case "/":
                            c = (b == 0) ? ((int) Double.NaN) : (a / b);
                            break;

                        default:
                            c = (int) Double.NaN;
                            break;
                    }

                    tempResult.setText(String.valueOf(c));
                    result.setText("");
                }
            }
        });

    }

}
