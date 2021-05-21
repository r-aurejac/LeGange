package com.example.legange.Bloc;

import com.example.legange.UI.TextBaseFragment;
import com.example.legange.Navigation.ScreenType;

public class TextBloc extends AbstractBloc {

    protected String title;
    protected String description = "Aucune description";

    public TextBloc(ScreenType nextScreen, String title, String description)
    {
        super(nextScreen);
        this.title = title;
        this.description = description;
        blocFragment = TextBaseFragment.newInstance(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }


}
