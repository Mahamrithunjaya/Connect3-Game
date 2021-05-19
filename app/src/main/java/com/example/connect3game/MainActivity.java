package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = YELLOW, 1 = RED

    int activePlayer = 0;
    boolean gameIsActive = true;

    // 2 means Unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(600);

            for (int[] winningPosition : winningPositions) {

                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {

                    // Someone has WON

                    gameIsActive = false;
                    String winner = "RED";
                    if (gameState[winningPosition[0]] == 0) {

                        winner = "YELLOW";

                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " Has Won !!!" );

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;
                    for (int counterState : gameState) {

                        if (counterState == 2) {
                            gameIsOver = false;
                            break;
                        }

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText(" It's a DRAW ");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }
    }

    public void playAgain (View view) {

        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for(int i=0; i<gameState.length; i++) {

            gameState[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
