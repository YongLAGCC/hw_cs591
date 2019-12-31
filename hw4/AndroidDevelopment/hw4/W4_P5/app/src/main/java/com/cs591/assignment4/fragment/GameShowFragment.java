package com.cs591.assignment4.fragment;


import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs591.assignment4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameShowFragment extends Fragment {

    private ImageView ivGameShow;
    private GridLayout gridLayoutGameShowWord;
    private TextView[] tvArrayAttemptChars;

    public GridLayout getGridLayoutGameShowWord() {
        return gridLayoutGameShowWord;
    }

    public TextView[] gettvArrayAttemptChars() {
        return tvArrayAttemptChars;
    }

    public ImageView getIvGameShow() {
        return ivGameShow;
    }

    public GameShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_show, container, false);
        ivGameShow = v.findViewById(R.id.ivGameShowHangman);
        gridLayoutGameShowWord = v.findViewById(R.id.gridLayoutGameShowWord);
        return v;
    }

    public void initLetterBoard(char[] chars) {
        gridLayoutGameShowWord.removeAllViews();
        gridLayoutGameShowWord.setRowCount(1);
        gridLayoutGameShowWord.setColumnCount(chars.length+1);
        tvArrayAttemptChars = new TextView[chars.length];


        TextView holder = new TextView(getContext());
        android.support.v7.widget.GridLayout.LayoutParams tempParam= new android.support.v7.widget.GridLayout.LayoutParams(android.support.v7.widget.GridLayout.spec(
                android.support.v7.widget.GridLayout.UNDEFINED, GridLayout.FILL,1f),
                android.support.v7.widget.GridLayout.spec(android.support.v7.widget.GridLayout.UNDEFINED, android.support.v7.widget.GridLayout.FILL,1f));
        holder.setHeight(0);
        holder.setWidth(0);
        holder.setLayoutParams(tempParam);
        holder.setText(" ");
//            tvArrayAttemptChars[i].setText(" ");
        holder.setTypeface(null, Typeface.BOLD);
        holder.setTextSize(20);
        holder.setPaintFlags(holder.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        gridLayoutGameShowWord.addView(holder);

        for (int i = 0; i < chars.length; i++) {
            tvArrayAttemptChars[i] = new TextView(getContext());
            android.support.v7.widget.GridLayout.LayoutParams param= new android.support.v7.widget.GridLayout.LayoutParams(android.support.v7.widget.GridLayout.spec(
                    android.support.v7.widget.GridLayout.UNDEFINED, GridLayout.FILL,1f),
                    android.support.v7.widget.GridLayout.spec(android.support.v7.widget.GridLayout.UNDEFINED, android.support.v7.widget.GridLayout.FILL,1f));
            tvArrayAttemptChars[i].setHeight(0);
            tvArrayAttemptChars[i].setWidth(0);
            tvArrayAttemptChars[i].setLayoutParams(param);
            tvArrayAttemptChars[i].setText((!Character.isLetter(chars[i]) ? '?': chars[i])  + "");
//            tvArrayAttemptChars[i].setText(" ");
            tvArrayAttemptChars[i].setTypeface(null, Typeface.BOLD);
            tvArrayAttemptChars[i].setTextSize(20);
            tvArrayAttemptChars[i].setPaintFlags(tvArrayAttemptChars[i].getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            gridLayoutGameShowWord.addView(tvArrayAttemptChars[i]);
        }
    }
}
