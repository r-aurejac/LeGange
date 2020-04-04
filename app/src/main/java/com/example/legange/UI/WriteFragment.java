package com.example.legange.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.legange.Class.Player;
import com.example.legange.Class.WritingRule;
import com.example.legange.R;
import com.example.legange.Class.Rule;
import com.example.legange.RuleInterface;

import java.util.ArrayList;
import java.util.Collections;


public class WriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private boolean isWritingPhase = true;
    // TODO: Rename and change types of parameters
    private ArrayList players;
    private WritingRule rule;
    private ArrayList<String> texts;
    private RuleInterface mListener;
    private Button validerButton;
    private EditText editText;
    private FrameLayout frameLayout;
    int phases =0;

    public WriteFragment() {
        // Required empty public constructor
    }


    public static WriteFragment newInstance( WritingRule rule,ArrayList<Player> players, int phases) {
        WriteFragment fragment = new WriteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, players);
        args.putSerializable(ARG_PARAM2, rule);
        args.putInt(ARG_PARAM3, phases);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            players = (ArrayList<Player>) getArguments().getSerializable(ARG_PARAM1);
            rule = (WritingRule) getArguments().getSerializable(ARG_PARAM2);
            phases = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_write, container, false);
        texts = new ArrayList<>();
        editText = (EditText) view.findViewById(R.id.text_edit);
        validerButton = (Button) view.findViewById(R.id.valider_button);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
        frameLayout = view .findViewById(R.id.frame_layout);
        return view;
    }



    private void validation()
    {
        if(isWritingPhase) {
            texts.add(editText.getText().toString());
            editText.setText("");
            if (texts.size() == players.size()-1) {
                Collections.shuffle(texts);
                read();

            }
            else if ( rule.phases == 1)
            {
                mListener.OnChefRuleEnd(texts.get(0));
            }
        }
        else mListener.toPlayerSelection();
    }
    private void read()
    {
        isWritingPhase =false;
        frameLayout.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(getContext());
        TextView tv;
        for(String string : texts) {
            tv = new TextView(getContext());
            tv.setText(string);
            tv.setTextSize(35);
            linearLayout.addView(tv);
        }
        scrollView.addView(linearLayout);
        frameLayout.addView(scrollView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RuleInterface) {
            mListener = (RuleInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
