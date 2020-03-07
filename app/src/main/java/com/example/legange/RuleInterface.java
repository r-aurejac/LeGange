package com.example.legange;

public interface RuleInterface {
    void onRuleEnd();

    void toScore();
    void toNextRule();
    void toPlayerSelection();
    void OnChefRuleEnd(String rule);

}
