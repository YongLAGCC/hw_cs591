package com.cs591.assignment4;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs591.assignment4.fragment.GameShowFragment;
import com.cs591.assignment4.fragment.HintFragment;
import com.cs591.assignment4.fragment.LetterSelectionFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GameActivity extends AppCompatActivity {


    private Fragment gameLettersFragment;
    private Fragment gameShowFragment;
    private Fragment gameHintFragment;

    private Button[] btnArraysLetters;
    private TextView[] tvArrayAttempWords;
    private ImageButton btnGameRefresh;
    private ImageView ivGameShow;
    private Drawable[] gameStates;
    private TextView tvHintInfo;
    private Button btnHint;

    private Map<String, String> stringHintMapping;
    private ArrayList<String> questions;

    private Map<Character, List<Integer>> targetLocationInfo;
    private Set<Integer> unavailableNum;
    private Set<Character> correctlyUsedChars;
    private Set<Character> incorrectlyUsedChars;
    private String currentTarget;
    private char[] currentAttempt;
    private int clickTimes;
    private int stateCursor;

    private boolean isLandscape;

    private static final String TAG = "GameActivity";
    private static final String CURRENT_STATE_CURSOR = "stateCursor";
    private static final String CURRENT_CLICK_TIMES = "clickTimes";
    private static final String CURRENT_ATTEMPT = "currentAttempt";
    private static final String CURRENT_TARGET = "currentTarget";
    private static final String CURRENT_TARGETINFO = "targetInfo";
    private static final String CURRENT_CORRECTLY_USED_CHARS = "correctUsedChars";
    private static final String CURRENT_INCORRECTLY_USED_CHARS = "incorrectUsedChars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FragmentManager fragmentManager = getSupportFragmentManager();
        gameShowFragment = fragmentManager.findFragmentById(R.id.frameGameShow);
        gameLettersFragment = fragmentManager.findFragmentById(R.id.frameGameLetters);
        gameHintFragment = fragmentManager.findFragmentById(R.id.frameGameHint);

        if (gameShowFragment == null) {
            gameShowFragment = new GameShowFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.frameGameShow, gameShowFragment)
                    .commit();
        }

        if (gameLettersFragment == null) {
            gameLettersFragment = new LetterSelectionFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.frameGameLetters, gameLettersFragment)
                    .commit();
        }

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            if (gameHintFragment == null) {
                gameHintFragment = new HintFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.frameGameHint, gameHintFragment)
                        .commit();
            }
        }
        Log.d(TAG, "" + (gameHintFragment == null));
        dataInit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        UIInit();
        gameInit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_CLICK_TIMES, clickTimes);

        if (stateCursor == gameStates.length) outState.putInt(CURRENT_STATE_CURSOR, -2);
        else if (correctlyUsedChars.size() == targetLocationInfo.size()) outState.putInt(CURRENT_STATE_CURSOR, -1);
        else  outState.putInt(CURRENT_STATE_CURSOR, stateCursor);

        outState.putString(CURRENT_TARGET, currentTarget);
        outState.putCharArray(CURRENT_ATTEMPT, currentAttempt);
        outState.putSerializable(CURRENT_CORRECTLY_USED_CHARS, (HashSet)correctlyUsedChars);
        outState.putSerializable(CURRENT_INCORRECTLY_USED_CHARS, (HashSet)incorrectlyUsedChars);
        outState.putSerializable(CURRENT_TARGETINFO, (HashMap)targetLocationInfo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        clickTimes = savedInstanceState.getInt(CURRENT_CLICK_TIMES);
        stateCursor = savedInstanceState.getInt(CURRENT_STATE_CURSOR);
        currentTarget = savedInstanceState.getString(CURRENT_TARGET);
        currentAttempt = savedInstanceState.getCharArray(CURRENT_ATTEMPT);
        correctlyUsedChars = (Set)savedInstanceState.getSerializable(CURRENT_CORRECTLY_USED_CHARS);
        incorrectlyUsedChars = (Set)savedInstanceState.getSerializable(CURRENT_INCORRECTLY_USED_CHARS);
        targetLocationInfo = (Map)savedInstanceState.getSerializable(CURRENT_TARGETINFO);

        for (Button button : btnArraysLetters) {
            char temp = button.getText().charAt(0);
            if (incorrectlyUsedChars.contains(temp) || correctlyUsedChars.contains(temp)) {
                button.setEnabled(false);
            }
        }
        if (stateCursor == -2) {
            ivGameShow.setImageDrawable(getDrawable(R.drawable.state_lose));
            for (Button button : btnArraysLetters) {
                button.setEnabled(false);
            }
        }
        else if (stateCursor == -1) ivGameShow.setImageDrawable(getDrawable(R.drawable.state_win));
        else ivGameShow.setImageDrawable(gameStates[stateCursor]);

        ((GameShowFragment)gameShowFragment).initLetterBoard(currentAttempt);
        tvArrayAttempWords = ((GameShowFragment)gameShowFragment).gettvArrayAttemptChars();

        if (isLandscape) {
            if (clickTimes >= 1) tvHintInfo.setText(stringHintMapping.get(currentTarget));
            if (clickTimes == 2) btnHint.setEnabled(false);
        }
    }

    private void UIInit() {
        btnArraysLetters = ((LetterSelectionFragment)gameLettersFragment).getBtnArrayLetterSelection();
        for (Button button : btnArraysLetters) {
            button.setOnClickListener(e -> letterChosen(e));
        }
        btnGameRefresh = ((LetterSelectionFragment)gameLettersFragment).getBtnLetterRefresh();
        btnGameRefresh.setOnClickListener(e -> gameInit());
        ivGameShow = ((GameShowFragment)gameShowFragment).getIvGameShow();
        gameStates = new Drawable[6];
        gameStates[0] = getDrawable(R.drawable.state_0);
        gameStates[1] = getDrawable(R.drawable.state_1);
        gameStates[2] = getDrawable(R.drawable.state_2);
        gameStates[3] = getDrawable(R.drawable.state_3);
        gameStates[4] = getDrawable(R.drawable.state_4);
        gameStates[5] = getDrawable(R.drawable.state_5);

        if (isLandscape) {
            btnHint = ((HintFragment)gameHintFragment).getBtnHintHint();
            btnHint.setOnClickListener(e -> hintChosen());

            tvHintInfo = ((HintFragment)gameHintFragment).getTvHintWord();
        }
    }

    private void dataInit() {
        targetLocationInfo = new HashMap();
        unavailableNum = new HashSet();
        correctlyUsedChars = new HashSet();
        incorrectlyUsedChars = new HashSet();
        questions = new ArrayList();
        stringHintMapping = new HashMap();
        String rawData = getString(R.string.game_library);
        String[] pairedData = rawData.split("-");
        for (String s : pairedData) {
            String[] temp = s.split(",");
            questions.add(temp[0]);
            stringHintMapping.put(temp[0], temp[1]);
        }
    }


    private void gameInit() {
        clickTimes = 0;
        stateCursor = 0;
        targetLocationInfo.clear();
        correctlyUsedChars.clear();
        incorrectlyUsedChars.clear();
        unavailableNum.clear();

        currentTarget = questions.get((int)(Math.random() * questions.size()));
        currentAttempt = new char[currentTarget.length()];
        for (int i = 0 ; i < currentAttempt.length; i++) {
            currentAttempt[i] = ' ';
        }
        updateStringInfo();

        clearView();
    }

    private void clearView() {
        for (Button button : btnArraysLetters) {
            button.setEnabled(true);
        }
        ivGameShow.setImageDrawable(gameStates[stateCursor]);
        ((GameShowFragment)gameShowFragment).initLetterBoard(currentAttempt);
        tvArrayAttempWords = ((GameShowFragment)gameShowFragment).gettvArrayAttemptChars();
        if (isLandscape) {
            tvHintInfo.setText("");
            btnHint.setEnabled(true);
        }
    }

    private int getRandomProblem() {
        int result;
        do {
            result = (int)(Math.random() * questions.size());
        } while (unavailableNum.contains(result));
        unavailableNum.add(result);
        return result;
    }

    private void updateStringInfo() {
        for (int i = 0; i < currentTarget.length(); i++) {
            char temp = currentTarget.charAt(i);
            if (targetLocationInfo.containsKey(temp)) targetLocationInfo.get(temp).add(i);
            else targetLocationInfo.put(temp, new ArrayList<Integer>(Arrays.asList(i)));
        }
    }

    private void disableHalfChars() {
        int totalNum = (26 - targetLocationInfo.size() - incorrectlyUsedChars.size()) / 2;
        int i = 0;
        while (i < totalNum) {
            char temp = (char) ('A' + (int)(Math.random() * 26));
            if (!incorrectlyUsedChars.contains(temp) && !targetLocationInfo.containsKey(temp)) {
                incorrectlyUsedChars.add(temp);
                btnArraysLetters[temp - 'A'].setEnabled(false);
                i++;
            }
        }
    }

    private void letterChosen(View v) {
        Button selectedBtn = (Button)v;
        char selectedChar = selectedBtn.getText().charAt(0);
        if (targetLocationInfo.containsKey(selectedChar)) {
            correctlyUsedChars.add(selectedChar);
            for (int i : targetLocationInfo.get(selectedChar)) {
                currentAttempt[i] = selectedChar;
                tvArrayAttempWords[i].setText(selectedChar + "");
            }
            if (correctlyUsedChars.size() == targetLocationInfo.size()) {
                for (Button button : btnArraysLetters) button.setEnabled(false);
                ivGameShow.setImageDrawable(getDrawable(R.drawable.state_win));
                if (isLandscape) btnHint.setEnabled(false);
            }
        } else {
            incorrectlyUsedChars.add(selectedChar);
            if (++stateCursor == gameStates.length) {
                for (Button button : btnArraysLetters) button.setEnabled(false);
                ivGameShow.setImageDrawable(getDrawable(R.drawable.state_lose));
                if (isLandscape) btnHint.setEnabled(false);
            } else ivGameShow.setImageDrawable(gameStates[stateCursor]);
        }
        selectedBtn.setEnabled(false);
    }

    private void hintChosen() {
        if (++clickTimes == 1) {
            tvHintInfo.setText(stringHintMapping.get(currentTarget));
        } else {
            disableHalfChars();
            btnHint.setEnabled(false);
        }
    }

    private String formatGameWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : currentAttempt) {
            sb.append(c);
            sb.append(' ');
        }
        return sb.toString().trim();
    }
}
