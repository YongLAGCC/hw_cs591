package com.cs591.assignment3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private static final String EXTRA_USERNAME = "com.cs591.assignment3.username";

    private int progress;
    private int corrects;
    private int result;

    private Button btnGameGenerate;
    private Button btnGameSubmit;

    private TextView tvGameNum1;
    private TextView tvGameNum2;
    private TextView tvGameInfo;

    private EditText etGameAnswer;

    private static final String PROGRESS = "progress";
    private static final String CORRECT = "result";
    private static final String NUM1 = "num1";
    private static final String NUM2 = "num2";
    private static final String RESULT = "result";


    public static Intent newIntent(Context packageContext, String username) {
        Intent intent = new Intent(packageContext, GameActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvGameNum1 = findViewById(R.id.tvGameNum1);
        tvGameNum2 = findViewById(R.id.tvGameNum2);
        tvGameInfo = findViewById(R.id.tvGameGameInfo);
        etGameAnswer = findViewById(R.id.etGameAnswer);

        btnGameGenerate = findViewById(R.id.btnGameGenerate);
        btnGameGenerate.setOnClickListener(e -> start());

        btnGameSubmit = findViewById(R.id.btnGameSubmit);
        btnGameSubmit.setOnClickListener(e -> submit());

        if (savedInstanceState != null) restore(savedInstanceState);
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Welcome <" + getIntent().getStringExtra(EXTRA_USERNAME) + ">", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 20);
            toast.show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PROGRESS, progress);
        outState.putInt(CORRECT, corrects);
        outState.putInt(RESULT, result);
        if (progress != 0) {
            outState.putInt(NUM1, Integer.parseInt(tvGameNum1.getText().toString()));
            outState.putInt(NUM2, Integer.parseInt(tvGameNum2.getText().toString()));
        }
    }

    public void start() {
        clear();
        tvGameInfo.setVisibility(View.VISIBLE);
        progress++;
        generateProblem();
    }

    public void clear() {
        progress = 0;
        corrects = 0;
        tvGameInfo.setVisibility(View.INVISIBLE);
        tvGameNum1.setText("");
        tvGameNum2.setText("");
        etGameAnswer.setText("");
    }

    public void submit() {
        if (progress == 0) return;
        checkAnswer();
        if (++progress > 10) {
            Toast toast = Toast.makeText(getApplicationContext(), "Score: " + corrects + " out of 10", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 20);
            toast.show();
            clear();
        } else {
            generateProblem();
            etGameAnswer.setText("");
        }
    }

    public void generateProblem() {
        int small1 = (int)(Math.random()*20) + 1;
        int small2 = (int)(Math.random()*20) + 1;
        int target = small1 * small2;

        tvGameNum1.setText(String.valueOf(target));
        tvGameNum2.setText(String.valueOf(small1));
        result = small2;
        tvGameInfo.setText("Progress : " + progress + "/10");
    }

    public void checkAnswer() {
        try {
            int userAnswer = Integer.parseInt(etGameAnswer.getText().toString());
            corrects += userAnswer == result ? 1 : 0;
        } catch (Exception e) {
            ;
        }
    }

    public void restore(Bundle bundle) {
        progress = bundle.getInt(PROGRESS);
        corrects = bundle.getInt(CORRECT);
        result = bundle.getInt(RESULT);
        if (progress != 0) {
            tvGameInfo.setText("Progress : " + progress + "/10");
            tvGameInfo.setVisibility(View.VISIBLE);
            tvGameNum1.setText(bundle.getInt(NUM1) + "");
            tvGameNum2.setText(bundle.getInt(NUM2) + "");
        }
    }
}
