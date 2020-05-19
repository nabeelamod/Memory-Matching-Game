package com.amod.memorymatchinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Memory Game");
        Button startBTN = (Button) findViewById(R.id.resetButton);
        startBTN.performClick();
    }

    public void restartGame(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Cards Shuffled, Begin!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();

        final Integer[] images = new Integer[]{R.drawable.alien,R.drawable.angry,R.drawable.earth,
                R.drawable.ghost,R.drawable.joy,R.drawable.sunglasses,R.drawable.alien,
                R.drawable.angry,R.drawable.earth,R.drawable.ghost,R.drawable.joy,R.drawable.sunglasses};

        final Button[] buttons = new Button[]{(Button) findViewById(R.id.button1),(Button) findViewById(R.id.button2),
                (Button) findViewById(R.id.button3),(Button) findViewById(R.id.button4),(Button) findViewById(R.id.button5),
                (Button) findViewById(R.id.button6),(Button) findViewById(R.id.button7),(Button) findViewById(R.id.button8),
                (Button) findViewById(R.id.button9),(Button) findViewById(R.id.button10),(Button) findViewById(R.id.button11),
                (Button) findViewById(R.id.button12)};

        Collections.shuffle(Arrays.asList(images));
        final int[][] clicked = {{0}};
        final int[] lastClicked = {-1};
        final int[] points = {0};

        for (int i = 0; i < 12; i++) {
            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].setBackgroundResource(R.drawable.square);
        }

        for (int i = 0; i < 12; i++){
            buttons[i].setText("back");
            buttons[i].setTextSize(0.0F);
            final int finalI = i;

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttons[finalI].getText() == "back" && clicked[0][0] < 2 ){
                        buttons[finalI].setBackgroundResource(images[finalI]);
                        buttons[finalI].setText(images[finalI]);
                        if (clicked[0][0] == 0) {
                            lastClicked[0] = finalI;
                        }
                        clicked[0][0]++;
                    }

                    if(clicked[0][0]==2) {
                        if (buttons[finalI].getText() == buttons[lastClicked[0]].getText()) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Match!", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.TOP, 0, 0);
                                    toast.show();
                                    clicked[0][0] = 0;
                                    points[0]++;
                                    if (points[0] == 6) {
                                        Toast toast1 = Toast.makeText(getApplicationContext(), "Winner! Congratulations", Toast.LENGTH_LONG);
                                        toast1.setGravity(Gravity.CENTER, 0, 0);
                                        toast1.show();
                                    }
                                    buttons[finalI].setVisibility(View.INVISIBLE);
                                    buttons[lastClicked[0]].setVisibility(View.INVISIBLE);
                                }
                            }, 1000);
                        }
                        else{
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    clicked[0][0] = 0;
                                    buttons[finalI].setBackgroundResource(R.drawable.square);
                                    buttons[lastClicked[0]].setBackgroundResource(R.drawable.square);
                                    buttons[finalI].setText("back");
                                    buttons[lastClicked[0]].setText("back");
                                }
                            }, 1000);
                        }
                    }
                }
            });
        }
    }
}
