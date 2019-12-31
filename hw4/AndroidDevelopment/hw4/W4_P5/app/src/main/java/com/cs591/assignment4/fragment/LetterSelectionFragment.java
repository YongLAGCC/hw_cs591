package com.cs591.assignment4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.cs591.assignment4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LetterSelectionFragment extends Fragment {

    private Button[] btnArrayLetterSelection;
    private GridLayout gridLayoutLetterSelectionLetters;
    private ImageButton btnLetterRefresh;

    public ImageButton getBtnLetterRefresh() {
        return btnLetterRefresh;
    }

    public Button[] getBtnArrayLetterSelection() {
        return btnArrayLetterSelection;
    }

    public LetterSelectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_letters, container, false);

        gridLayoutLetterSelectionLetters = v.findViewById(R.id.gridLayoutGameLetters);
        gridLayoutLetterSelectionLetters.setRowCount(3);
        gridLayoutLetterSelectionLetters.setColumnCount(9);

        btnLetterRefresh = v.findViewById(R.id.btnGameRefresh);

        initLetterBoard();
        return v;
    }

    public void initLetterBoard() {
        btnArrayLetterSelection = new Button[26];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int currentNum = i * 9 + j;
                if (currentNum == 26) return;
                btnArrayLetterSelection[currentNum] = new Button(getContext());
                btnArrayLetterSelection[currentNum].setText((char)(i*9+j + 'A') + "");
                GridLayout.LayoutParams param= new GridLayout.LayoutParams(GridLayout.spec(
                        GridLayout.UNDEFINED,GridLayout.FILL,1f),
                        GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL,1f));
                param.height = 0;
                param.width = 0;
                btnArrayLetterSelection[currentNum].setLayoutParams(param);
                gridLayoutLetterSelectionLetters.addView(btnArrayLetterSelection[currentNum]);
            }
        }

    }

}
