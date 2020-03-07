package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.legange.Class.Player;
import com.example.legange.R;
import com.example.legange.str;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button jouerButton,creditButton,testButton;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jouerButton = (Button) findViewById(R.id.jouer_button);
        jouerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent intent = AddPlayerActivity.newIntent(getApplicationContext());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        creditButton = (Button) findViewById(R.id.button_credit);
        creditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = AddPlayerActivity.newIntent(getApplicationContext());
                getApplicationContext().startActivity(intent);
            }
        });

        testButton = (Button) findViewById(R.id.button_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<Player> players = new ArrayList<>();
                for(int i =0; i< str.MAX_PLAYER_NUMBER; i++)
                {
                    String name = "Joueur" + String.valueOf(i+1);
                    players.add(new Player(name));
                }
                Intent intent = GameActivity.newIntent(getApplicationContext(), players);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });


    }
}
