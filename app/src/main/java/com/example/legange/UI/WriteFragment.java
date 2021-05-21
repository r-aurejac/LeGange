    package com.example.legange.UI;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.FrameLayout;
    import android.widget.LinearLayout;
    import android.widget.ScrollView;
    import android.widget.TextView;

    import com.example.legange.Navigation.ScreenType;
    import com.example.legange.Player.PlayersManager;
    import com.example.legange.Bloc.WriteTextBloc;
    import com.example.legange.R;

    import java.util.ArrayList;
    import java.util.Collections;


    public class WriteFragment extends BaseFragment {


        private static final String PHASES = "phases";
        private ArrayList<String> texts;

        private Button validerButton;
        private EditText editText;
        private FrameLayout frameLayout;
        int phases =0;
        boolean finished = false;
        public WriteFragment() {
            // Required empty public constructor
        }


        public static WriteFragment newInstance(int phases) {
            WriteFragment fragment = new WriteFragment();
            Bundle args = new Bundle();
            args.putInt(PHASES, phases);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {

                phases = getArguments().getInt(PHASES);
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
            if (finished)
            {
                toNextScreen(ScreenType.SCORE);
            }
                texts.add(editText.getText().toString());
                editText.setText("");
                if (texts.size() == phases) {
                    Collections.shuffle(texts);
                    read();
                    validerButton.setText("suivant");
                    finished =true;
                }

        }

        private void read()
        {
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


    }
