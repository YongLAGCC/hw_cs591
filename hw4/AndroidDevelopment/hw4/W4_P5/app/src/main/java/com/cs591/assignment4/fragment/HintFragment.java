package com.cs591.assignment4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cs591.assignment4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HintFragment extends Fragment {

    private TextView tvHintWord;
    private Button btnHintHint;

    public TextView getTvHintWord() {
        return tvHintWord;
    }

    public Button getBtnHintHint() {
        return btnHintHint;
    }

    public HintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_hint, container, false);
        btnHintHint = v.findViewById(R.id.btnHintHint);
        tvHintWord = v.findViewById(R.id.tvHintHint);
        return v;
    }

}
