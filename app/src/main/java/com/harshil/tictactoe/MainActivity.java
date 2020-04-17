package com.harshil.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;       //0:yellow, 1:red
    int[] gameState = {2,2,2,2,2,2,2,2,2};      //2 represents empty
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);             //to get out of screen
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow_circle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_circle);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[0]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Someone has won!
                    gameActive = false;
                    String winner;

                    if (activePlayer == 1) {      //opposite since we changed Active Player
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++){           //to remove all images
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer = 0;       //0:yellow, 1:red
        gameActive = true;
        for (int i =0;i<gameState.length;i++){
            gameState[i] = 2;      //2 represents empty
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
