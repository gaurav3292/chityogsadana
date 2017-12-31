package com.cityogsadana.utils;

import com.cityogsadana.bean.QuestionBean;

import java.util.ArrayList;

/**
 * Created by pc15 on 10/23/2017.
 */

public class DataEntry {

    public ArrayList<QuestionBean> getEmoList() {
        ArrayList<QuestionBean> emoList = new ArrayList<>();
        emoList.add(new QuestionBean("I experience worry and anxiety"));
        emoList.add(new QuestionBean("My mind is always busy"));
        emoList.add(new QuestionBean("I get stressed easily"));
        emoList.add(new QuestionBean("I can feel depressed easily"));
        emoList.add(new QuestionBean("I suffer from panic attacks"));
        emoList.add(new QuestionBean("I get jealous easily"));
        emoList.add(new QuestionBean("I forget things"));
        emoList.add(new QuestionBean("I am a sensitive and compassionate"));
        emoList.add(new QuestionBean("I get angry and snappy easily"));
        emoList.add(new QuestionBean("I feel content"));
        emoList.add(new QuestionBean("I forgive easily"));
        emoList.add(new QuestionBean("I can’t control my lusts"));
        emoList.add(new QuestionBean("I meditate regularly"));
        emoList.add(new QuestionBean("I have difficulty emptying my mind"));
        emoList.add(new QuestionBean("My work environment isn’t very good"));
        emoList.add(new QuestionBean("I meditate but still suffer from anxiety, worry, anger and frustration"));
        emoList.add(new QuestionBean("I meditate but still suffer from panic attacks"));
        emoList.add(new QuestionBean("I meditate but still take sleeping pills or anti-depressants"));
        emoList.add(new QuestionBean("I am affectionate"));
        emoList.add(new QuestionBean("I lack the ability to get along with people"));
        emoList.add(new QuestionBean("My self-esteem and self-confidence is lacking"));
        emoList.add(new QuestionBean("I have feeling of security"));
        emoList.add(new QuestionBean("I lack the ability to adjust easily in every situation"));
        emoList.add(new QuestionBean("My memory is not good"));
        emoList.add(new QuestionBean("I experience self-doubt"));
        emoList.add(new QuestionBean("I lack confidence in my ability"));
        emoList.add(new QuestionBean("I have trouble expressing myself"));
        return emoList;

    }

    public ArrayList<QuestionBean> getDependencyList() {
        ArrayList<QuestionBean> depenList = new ArrayList<>();
        depenList.add(new QuestionBean("I am currently taking anti-depressant medication"));
        depenList.add(new QuestionBean("I drink alcohol"));
        depenList.add(new QuestionBean("I smoke marijuana"));
        depenList.add(new QuestionBean("I have taken recreational drugs in the past"));
        depenList.add(new QuestionBean("I am taking prescribed medication for some physical ailment"));

        return depenList;
    }


    public ArrayList<QuestionBean> getSleepList() {
        ArrayList<QuestionBean> sleepList = new ArrayList<>();
        sleepList.add(new QuestionBean("My sleep is deep and peaceful"));
        sleepList.add(new QuestionBean("I have lots of dreams"));
        sleepList.add(new QuestionBean("My dreams are violent in nature"));
        sleepList.add(new QuestionBean("My sleep is light and interrupted"));
        sleepList.add(new QuestionBean("I wake up after 6.30 am in the morning"));
        sleepList.add(new QuestionBean("I feel sleepy and tired by 3.30pm in the afternoon"));

        return sleepList;
    }

    public ArrayList<QuestionBean> getPreviousHistoryList() {
        ArrayList<QuestionBean> historyList = new ArrayList<>();
        historyList.add(new QuestionBean("I have a history of depression"));
        historyList.add(new QuestionBean("I am under a lot of stress"));
        historyList.add(new QuestionBean("I had a terrible childhood"));
        historyList.add(new QuestionBean("I have unstable family relationships"));
        historyList.add(new QuestionBean("I don’t have much support in my life"));
        historyList.add(new QuestionBean("I don’t have high self esteem"));
        historyList.add(new QuestionBean("I have a lot of broken relationships"));
        historyList.add(new QuestionBean("I don’t have a good relationship with my parents"));
        historyList.add(new QuestionBean("I have abused relationships"));
        historyList.add(new QuestionBean("I enjoy learning new subjects"));
        historyList.add(new QuestionBean("I love hot weather"));
        historyList.add(new QuestionBean("I love hot showers"));
        historyList.add(new QuestionBean("I have been in abusive relationships in the past"));
        historyList.add(new QuestionBean("I have run away from my responsibilities"));

        return historyList;
    }

    public ArrayList<QuestionBean> getEthicsList() {
        ArrayList<QuestionBean> ethicsList = new ArrayList<>();
        ethicsList.add(new QuestionBean("I help people without expectations"));
        ethicsList.add(new QuestionBean("I tell lies quite often"));
        ethicsList.add(new QuestionBean("I believe in preaching & practicing my beliefs"));
        ethicsList.add(new QuestionBean("I run away from difficult situations"));
        ethicsList.add(new QuestionBean("I am dedicated to my family"));
        ethicsList.add(new QuestionBean("I am not happy at work"));
        ethicsList.add(new QuestionBean("I don’t believe in ethics"));
        ethicsList.add(new QuestionBean("I am materialistic"));
        ethicsList.add(new QuestionBean("I want to be rich"));
        ethicsList.add(new QuestionBean("I don’t mind using shortcuts to be successful"));
        ethicsList.add(new QuestionBean("I blame myself for any failures in my life"));
        ethicsList.add(new QuestionBean("I try to blame others for my failures"));
        ethicsList.add(new QuestionBean("I respect my elders"));
        ethicsList.add(new QuestionBean("I have good control over my desires"));
        ethicsList.add(new QuestionBean("I have good control over my emotions"));
        ethicsList.add(new QuestionBean("I have good control over my speech"));
        ethicsList.add(new QuestionBean("I have good control over my actions"));


        return ethicsList;
    }


    public ArrayList<QuestionBean> getDietList() {
        ArrayList<QuestionBean> dietList = new ArrayList<>();
        dietList.add(new QuestionBean("I eat meat x12 times a week"));
        dietList.add(new QuestionBean("I eat meat x6 times a week"));
        dietList.add(new QuestionBean("I eat tinned foods often"));
        dietList.add(new QuestionBean("I use processed foods often"));
        dietList.add(new QuestionBean("I eat organic tinned foods"));
        dietList.add(new QuestionBean("I drink alcohol x5 times week"));
        dietList.add(new QuestionBean("I drink alcohol x3 times a week"));
        dietList.add(new QuestionBean("I drink Fiji Water 6-7 times a week"));
        dietList.add(new QuestionBean("I drink coffee 2-3 times a week"));
        dietList.add(new QuestionBean("I eat a lot of frozen foods"));
        dietList.add(new QuestionBean("I suffer from constipation"));
        dietList.add(new QuestionBean("I am overweight"));
        dietList.add(new QuestionBean("I have a good appetite"));


        return dietList;
    }

    public ArrayList<QuestionBean> getLevelFourList() {
        ArrayList<QuestionBean> levelFourList = new ArrayList<>();
        levelFourList.add(new QuestionBean("Get angry?"));
        levelFourList.add(new QuestionBean("Feel jealous?"));
        levelFourList.add(new QuestionBean("Feel hatred towards another person?"));
        levelFourList.add(new QuestionBean("Feel frustrated?"));
        levelFourList.add(new QuestionBean("Cheat on someone?"));
        levelFourList.add(new QuestionBean("Steal anything?"));
        levelFourList.add(new QuestionBean("Get nervous?"));
        levelFourList.add(new QuestionBean("Tell a lie?"));
        levelFourList.add(new QuestionBean("Do anything which you were not supposed to do?"));
        levelFourList.add(new QuestionBean("Say something hurtful to your parents?"));
        levelFourList.add(new QuestionBean("Get upset with your children?"));
        levelFourList.add(new QuestionBean("Treat your employees badly?"));
        levelFourList.add(new QuestionBean("Deal badly with a work colleague?"));

        return levelFourList;
    }


    public ArrayList<QuestionBean> getLevelOneList() {
        ArrayList<QuestionBean> levelOneList = new ArrayList<>();
        levelOneList.add(new QuestionBean("Get up early?"));
        levelOneList.add(new QuestionBean("Brush your teeth and scrape your tongue?"));
        levelOneList.add(new QuestionBean("Make your morning de-tox drink?"));
        levelOneList.add(new QuestionBean("Apply Nasal drops?"));
        levelOneList.add(new QuestionBean("Avoid a hot shower?"));
        levelOneList.add(new QuestionBean("Self-massage with warm oil?"));
        levelOneList.add(new QuestionBean("Do your deep breathing?"));

        return levelOneList;


    }


    public ArrayList<QuestionBean> getlevelTwoList() {
        ArrayList<QuestionBean> levelTwoList = new ArrayList<>();
        levelTwoList.add(new QuestionBean("Frozen food"));
        levelTwoList.add(new QuestionBean("Tinned food"));
        levelTwoList.add(new QuestionBean("Processed food"));
        levelTwoList.add(new QuestionBean("Microwaved food"));
        levelTwoList.add(new QuestionBean("White bread"));
        levelTwoList.add(new QuestionBean("Coffee"));
        levelTwoList.add(new QuestionBean("Peanut Butter"));
        levelTwoList.add(new QuestionBean("Chilli"));
        levelTwoList.add(new QuestionBean("White sugar"));
        levelTwoList.add(new QuestionBean("Tomato sauce"));
        levelTwoList.add(new QuestionBean("Alcohol"));
        levelTwoList.add(new QuestionBean("Marijuana"));
        levelTwoList.add(new QuestionBean("Recreational drugs"));
        levelTwoList.add(new QuestionBean("Pre-cooked foods"));
        levelTwoList.add(new QuestionBean("Red meat"));

        return levelTwoList;
    }


    public ArrayList<QuestionBean> getlevelThreeList() {
        ArrayList<QuestionBean> levelThreeList = new ArrayList<>();
        levelThreeList.add(new QuestionBean("Eat your meal while watching TV"));
        levelThreeList.add(new QuestionBean("Eat while talking"));
        levelThreeList.add(new QuestionBean("Eat when emotionally upset"));
        levelThreeList.add(new QuestionBean("Eat on the run"));
        levelThreeList.add(new QuestionBean("Eat and drink together"));
        levelThreeList.add(new QuestionBean("Eat while on phone"));
        levelThreeList.add(new QuestionBean("Eat while playing on your computer"));
        levelThreeList.add(new QuestionBean("Cook when emotionally upset"));
        levelThreeList.add(new QuestionBean("Have bad thoughts while eating"));
        levelThreeList.add(new QuestionBean("Eat to just fill your tummy"));
        levelThreeList.add(new QuestionBean("Eat standing up"));
        return levelThreeList;

    }

    public ArrayList<QuestionBean> getLevelFour_OneList(){
        ArrayList<QuestionBean> levelFour_OneList = new ArrayList<>();
        levelFour_OneList.add(new QuestionBean("Get angry?"));
        levelFour_OneList.add(new QuestionBean("Feel jealous?"));
        levelFour_OneList.add(new QuestionBean("Feel hatred towards another person?"));
        levelFour_OneList.add(new QuestionBean("Feel frustrated?"));
        levelFour_OneList.add(new QuestionBean("Cheat on someone?"));
        levelFour_OneList.add(new QuestionBean("Steal anything?"));
        levelFour_OneList.add(new QuestionBean("Get nervous?"));
        levelFour_OneList.add(new QuestionBean("Tell a lie?"));
        levelFour_OneList.add(new QuestionBean("Do anything which you were not supposed to do?"));
        levelFour_OneList.add(new QuestionBean("Say something hurtful to your parents?"));
        levelFour_OneList.add(new QuestionBean("Get upset with your children?"));
        levelFour_OneList.add(new QuestionBean("Treat your employees badly?"));
        levelFour_OneList.add(new QuestionBean("Deal badly with a work colleague?"));

        return levelFour_OneList;


    }

    public ArrayList<QuestionBean> getLevelFour_TwoList(){
        ArrayList<QuestionBean> levelFour_TwoList = new ArrayList<>();
        levelFour_TwoList.add(new QuestionBean("Face the mirror"));


        return levelFour_TwoList;


    }


}
