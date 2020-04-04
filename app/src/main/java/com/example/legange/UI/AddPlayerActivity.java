package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.legange.RuleClasses.Player;
import com.example.legange.R;

import java.util.ArrayList;

public class AddPlayerActivity extends AppCompatActivity {

    private Button ajouterButton;
    private Button jouerButton;
    private EditText joueurEditText;
    private ArrayList<Player> joueurs;
    private LinearLayout linearLayout;
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddPlayerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        joueurs = new ArrayList<>();
        ajouterButton = (Button) findViewById(R.id.ajouter_button);
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ajouterJoueur();
            }
        });
        jouerButton = (Button) findViewById(R.id.lancer_button);
        jouerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start();
            }
        });
        joueurEditText = (EditText) findViewById(R.id.joueur_editText);
        linearLayout= (LinearLayout) findViewById(R.id.name_linear_layout);
    }

    private void ajouterJoueur()
    {
        if(nameIsValid()) {
            Player joueur = new Player(joueurEditText.getText().toString());
            joueurs.add(joueur);
            TextView tv = new TextView(getApplicationContext());
            tv.setText(joueurEditText.getText().toString());
            linearLayout.addView(tv);
            alertDialog(joueurEditText.getText().toString()+" ajouté");
            joueurEditText.setText("");

        }
    }

    private void alertDialog(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    private void start()
    {
        if (joueurs.size()>2) {
            Intent intent = GameActivity.newIntent(getApplicationContext(), joueurs);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }
        else alertDialog("Il faut au moins 3 kheys pour jouer");
    }
    private boolean nameIsValid()
    {
        if(joueurEditText.getText().toString().length()<3)
            return false;
        for(Player player : joueurs)
        {
            if (player.getName().equals(joueurEditText.getText().toString()))
                return false;

        }

        return true;
    }
    }

