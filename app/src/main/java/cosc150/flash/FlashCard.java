package cosc150.flash;


import java.util.Vector;

/**
 * Created by Darren on 4/8/17.
 */


public class FlashCard
{
    String charString;
    String meaning;
    String pinyin;
    int character;
    Vector <Integer> pastEight;
    double priority; //the higher the number, the more the user knows the flash card.
    boolean quizUpperCorrect;
    boolean quizLowerCorrect;
    //Queue<Integer> results;

    int recentMeaning = 0;


    public FlashCard( String cs, String p, String m)
    {
        charString = cs;
        pinyin = p;
        meaning = m;
        pastEight = new Vector<Integer>();
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        pastEight.add(0);
        priority = 0.0;
        quizUpperCorrect = false;
        quizLowerCorrect = false;
        ;
    }

    //getters
    public Vector<Integer> getPastEightAtempts() {return pastEight;}
    public String getMeaning() {return meaning;}
    public String getCharString() {return charString;}
    public double getPriority() {return priority;}
    public int getCharacter() {return character;}
    public String getPinyin() {return pinyin;}

    //setters
    public void setPinyin(String pinyin) {this.pinyin = pinyin;}
    public void setCharString(String charString) {this.charString = charString;}
    public void setMeaning(String meaning) {this.meaning = meaning;}
    public void setCharacter(String s)
    {
        int i3 = 0x2f20;
        for ( int i=0; i<100; i++ )
        {
            s += Character.toString((char) i3 ); i3++;
        }

        character = i3;
    }

    //this method removes the last node (oldest attempt) and adds the result to the front
    //(most current attempt.)
    public void updatePastEight(int result)
    {
        pastEight.remove(pastEight.size());
        pastEight.add(0,result);
    }


}




