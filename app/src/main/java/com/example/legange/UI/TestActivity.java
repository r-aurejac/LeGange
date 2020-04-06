package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.legange.R;
import com.example.legange.RuleInterface;

//Activité pour tester des fragments
public class TestActivity extends AppCompatActivity implements RuleInterface {

    LinearLayout linearLayout;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        linearLayout = (LinearLayout) findViewById(R.id.test_linear_layout);
        test();

    }



    private void test()
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //tester ici
        fragmentTransaction.add(R.id.test_linear_layout,BetFragment.newInstance("t","t"));

        fragmentTransaction.commit();
    }

    @Override
    public void onRuleEnd() {

    }

    @Override
    public void toScore() {

    }

    @Override
    public void toNextRule() {

    }

    @Override
    public void toPlayerSelection() {

    }

    @Override
    public void OnChefRuleEnd(String rule) {

    }
}
