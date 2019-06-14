package com.example.matematicadivertida;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class Equations extends AppCompatActivity {

    String[] operator = new String[]{"+", "-"};
    private TextView equation;
    private TextView firstNumber;
    private TextView secondNumber;
    private EditText editText;
    private Button next;

    private int score;
    private int questionsAsked;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equations);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        questionsAsked = intent.getIntExtra("questionNumber", 0);

        equation = findViewById(R.id.operator);
        int index = new Random().nextInt(2);
        equation.setText(operator[index]);

        firstNumber = findViewById(R.id.firstNumber);
        firstNumber.setText(String.format("%d", new Random().nextInt(10)));
        secondNumber = findViewById(R.id.secondNumber);
        secondNumber.setText(String.format("%d", new Random().nextInt(10)));
        editText = findViewById(R.id.editText);
        next = findViewById(R.id.answerButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(Equations.this);

                if (isAnswerCorrect()) {
                    b.setMessage("Parabéns, você acertou!");
                    score += 1;
                } else {
                    b.setMessage("Você errou! A resposta certa era: " + getAnswer());
                }

                b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog = b.create();
                alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (questionsAsked < 4 ) {
                            Intent intent = new Intent(Equations.this, Equations.class);
                            intent.putExtra("score", score);
                            intent.putExtra("questionNumber", questionsAsked + 1);
                            startActivity(intent);

                        } else {
                            Intent intent = new Intent(Equations.this, Result.class);
                            intent.putExtra("score", score);
                            intent.putExtra("questionNumber", questionsAsked + 1);
                            startActivity(intent);
                        }
                    }
                });

                alertDialog.show();
            }
        });

    }

    private Boolean isAnswerCorrect() {
        int correctAnswer = getAnswer();
        int answer = 0;
        try {
            answer = Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            AlertDialog.Builder b = new AlertDialog.Builder(Equations.this);
            b.setMessage("Valor inválido");

            b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

            alertDialog = b.create();
            alertDialog.show();
        }


        return correctAnswer == answer;
    }

    private int getAnswer() {
        int first = Integer.parseInt(firstNumber.getText().toString());
        int second = Integer.parseInt(secondNumber.getText().toString());

        switch (equation.getText().toString()) {
            case "+":
                return first + second;
            case "-":
                return first - second;
        }

        return 0;
    }
}
