package com.muqaddas.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        setListeners();
    }

    private void setListeners() {
        int[] ids = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDivide,
                R.id.btnClear, R.id.btnEqual
        };

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            String value = b.getText().toString();

            switch (value) {
                case "=":
                    double result = calculate(input);
                    input = Double.isNaN(result) ? "Error" : String.valueOf(result);
                    break;
                case "C":
                    input = "";
                    break;
                default:
                    input += value;
            }

            tvResult.setText(input);
        };

        for (int id : ids) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private double calculate(String expression) {
        try {
            Expression exp = new ExpressionBuilder(expression).build();
            return exp.evaluate();
        } catch (Exception e) {
            return Double.NaN;
        }
    }
}
