    package com.example.legange.UI;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.example.legange.R;
    import com.example.legange.Bloc.TextBloc;


    public class TextBaseFragment extends BaseFragment {

        private TextBloc rule;
        private TextView ruleText,titleText;
        public TextBaseFragment() {
            //resquired Empty Constructor
        }

        public static BaseFragment newInstance(TextBloc textRule) {

            BaseFragment ruleFragment = new TextBaseFragment();
            Bundle data = new Bundle();
            data.putSerializable(RULE,textRule);
            ruleFragment.setArguments(data);

            return ruleFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                rule = (TextBloc) getArguments().getSerializable(RULE);
            }

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


           View view = inflater.inflate(R.layout.fragment_rule, container, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigationInterface.toNextScreen(rule.nextScreen);
                }
            });
            ruleText = (TextView) view.findViewById(R.id.rule_text_view);
            ruleText.setText(rule.getDescription());
            ruleText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   navigationInterface.toNextScreen(rule.nextScreen);
                }
            });
            titleText =(TextView) view.findViewById(R.id.title_text_view);
            titleText.setText(rule.getTitle());
            return view;
        }






    }
