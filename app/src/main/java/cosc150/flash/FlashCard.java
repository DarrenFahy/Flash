package cosc150.flash;

/**
 * Created by Darren on 4/8/17.
 */


public class FlashCard
{
    public static final double EIGHT = 8;
    String charString;
    String meaning;
    String pinyin;
    int character;
    double pastEight;
    double priority; //the higher the number, the more the user knows the flash card.

    public FlashCard( String cs, String p, String m)
    {
        charString = cs;
        pinyin = p;
        meaning = m;
        pastEight = 0;
        priority = 0.0;

    }

    //getters
    public double getPastEightAtempts() {return pastEight;}
    public String getMeaning() {return meaning;}
    public String getCharString() {return charString;}
    public double getPriority() {return priority;}
    public int getCharacter() {return character;}
    public String getPinyin() {return pinyin;}

    //setters
    public void calculatePriority() {priority = getPastEightAtempts()/EIGHT;}
    public void setPinyin(String pinyin) {this.pinyin = pinyin;}
    public void incrementPastEightAtempts() {pastEight++;}
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


}




