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

    public FlashCard( String cs, String p, String m)
    {
        charString = cs;
        pinyin = p;
        meaning = m;

    }

    public void setCharacter( String s)
    {
        int i3 = 0x2f20;
        for ( int i=0; i<100; i++ )
        {
            s += Character.toString((char) i3 ); i3++;
        }

        character = i3;
    }


}




