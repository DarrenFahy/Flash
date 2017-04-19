package cosc150.flash;



/**
 * Created by Darren on 4/8/17.
 */


public class FlashCard
{
    String charString;
    String meaning;
    String pinyin;
    int character;
    int pastEight[];
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
        pastEight = new int[] {0,0,0,0,0,0,0,0};
        priority = 0.0;
        quizUpperCorrect = false;
        quizLowerCorrect = false;
        createQ();
    }

    //getters
    public int[] getPastEightAtempts() {return pastEight;}
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

    public void createQ()
    {
        for (int i =0; i<8; i++)
        {
            //results.add(0);
        }

    }


}




