package com.example.legange.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.legange.R;
import com.example.legange.Bloc.BlackJackBloc;


public class BlackJackFragment extends BaseFragment {


    private TextView totalText, playerNameText,valueText;
    private Button nextButton, drawButton;
    private BlackJackBloc blackJackRule;
    public BlackJackFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(BlackJackBloc blackJackRule) {

        BaseFragment ruleFragment = new BlackJackFragment();
        Bundle data = new Bundle();
        data.putSerializable(RULE,blackJackRule);
        ruleFragment.setArguments(data);

        return ruleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            blackJackRule = (BlackJackBloc) getArguments().getSerializable(RULE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_black_jack, container, false);

        drawButton = (Button) view.findViewById(R.id.relancer_button);
        nextButton = (Button) view.findViewById(R.id.suivant_button);
        valueText = (TextView) view.findViewById(R.id.value_text);
        totalText =  (TextView) view.findViewById(R.id.total_text);
        playerNameText = (TextView) view.findViewById(R.id.joueur_text);

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDrawButtonClicked();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextPlayerButtonClicked();
            }
        });

        update();
        return view;
    }



    private void update()
    {
        playerNameText.setText(blackJackRule.getCurrentPlayer().getName());
        valueText.setText(String.valueOf(0));
        totalText.setText(String.valueOf(0));
    }

    private void onDrawButtonClicked()
    {
        valueText.setText(String.valueOf(blackJackRule.draw()));
        totalText.setText(String.valueOf(blackJackRule.getCurrentTotal()));
    }

    private void onNextPlayerButtonClicked()
    {

        if(blackJackRule.nextPlayer())
        {
            update();
        }
        else
        {
            navigationInterface.toNextScreen(blackJackRule.nextScreen);
        }
    }



}
