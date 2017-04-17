package cosc150.flash;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main2Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        System.out.println("load file function");
        loadFile();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //set title to name of button clicked
        String str = extras.getString("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(str);
       // getWindow().setTitle(title.toString());


        TextView test1 = (TextView) findViewById(R.id.otherTV1);
        TextView test2 = (TextView) findViewById(R.id.otherTV2);

        if ( str.equals("Meaning"))
        {
            test1.setText("Pinyin");
            test2.setText("Character");
        }
        else if (str.equals("Pinyin"))
        {
            test1.setText("Meaning");
            test2.setText("Character");
        }
        else if (str.equals("Character"))
        {
            test1.setText("Meaning");
            test2.setText("Pinyin");
        }
        else if (str.equals("Random") )
        {
            test1.setText("Meaning");
            test2.setText("Character");
        }


        //Specify type of quiz based on switch
        Boolean type = extras.getBoolean("game_mode");
        String txt = extras.getString("text");

        //number of cards to test
        int numCards = extras.getInt("number_cards");

        System.out.println( "Game mode is: " + type);
        System.out.println( "And number of cards is: " + numCards + " and message: " + str);

        //loadFile();
        manualCards();
        System.out.println( "Tried my best to print cards");


    }

    public void importCards()
    {
        System.out.println("made it here!   1----------------------------------");
        View v;
        Thread t= new Thread(
                new Runnable ()
                {
                    public void run()
                    {  //never makes it to the runnable
                        System.out.println("made it here!   2----------------------------------");

                        try
                        {
                            System.out.println("made it here!   3----------------------------------");

                            // Create a URL for the desired page
                            URL url = new URL("http://people.cs.georgetown.edu/~bk620/chidi.txt");
                            // Read all the text returned by the server
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            String str;
                            Vector<FlashCard> cardList = new Vector<FlashCard>();

                            while ((str = in.readLine()) != null)
                            {
                                List<String> fclist = Arrays.asList(str.split(","));
                                System.out.println("Symbol "+fclist.get(0));
                                System.out.println("Pinyin "+fclist.get(1));
                                System.out.println("Added meaning "+fclist.get(2));

                                FlashCard fc = new FlashCard( fclist.get(0), fclist.get(1), fclist.get(2) );

                                cardList.add(fc);
                                System.out.println("Added meaning "+fclist.get(2));

                            }//end while

                            System.out.println("made it here!   2");
                            printFC(cardList);
                            in.close();
                        }//end try

                        catch (Exception e) {Log.i("MA.L----->", "error=" + e);}

                    }//end run()
                }//end runable()
        );//end thead

    }//end import cards

    public void loadFile()
    {
        System.out.println("ok im here");
        FileInputStream fis;
        final StringBuffer storedString = new StringBuffer();
        //BufferedReader in = new BufferedReader();

        try {
            fis = openFileInput("/Users/Darren/Downloads/dict.txt");

            String str;
            Vector<FlashCard> cardList = new Vector<FlashCard>();
            byte[] bbuf = new byte[50];
            while ((fis.read(bbuf, 0, 20)) != -1)
            {
                str = new String(bbuf);
                List<String> fclist = Arrays.asList(str.split(","));

                System.out.println("Symbol "+fclist.get(0));
                System.out.println("Pinyin "+fclist.get(1));
                System.out.println("Added meaning "+fclist.get(2));

                FlashCard fc = new FlashCard( fclist.get(0), fclist.get(1), fclist.get(2) );

                cardList.add(fc);
                System.out.println("Added meaning "+fclist.get(2));

            }//end while
        }
        catch  (Exception e)
        {
            System.out.println("some error...");
        }
    }
    public void manualCards()
    {
        Vector <FlashCard> cardList1 = new Vector<FlashCard>();

        FlashCard fc1 = new FlashCard("⼸","gōng","bow");
        cardList1.add(fc1);

        FlashCard fc2 = new FlashCard("⼳","yāo","tiny");
        cardList1.add(fc2);

        FlashCard fc3 = new FlashCard("⼯","gōng","work");
        cardList1.add(fc3);

        FlashCard fc4 = new FlashCard("⽇","rì","sun");
        cardList1.add(fc4);

        FlashCard fc5 = new FlashCard("⼼","xīn","heart");
        cardList1.add(fc5);

        FlashCard fc6 = new FlashCard("⼽","gē","dagger-axe");
        cardList1.add(fc6);

        FlashCard fc7 = new FlashCard("⼿","shǒu","hand");
        cardList1.add(fc7);

        FlashCard fc8 = new FlashCard("⽉","yuè","moon");
        cardList1.add(fc8);

        FlashCard fc9 = new FlashCard("⽊","mù","wood");
        cardList1.add(fc9);

        for (int i = 0; i<9; i++)
        {
            System.out.println(cardList1.get(i).meaning);
        }
    }


    public void printFC(List<FlashCard> d)
    {
        for (int i = 0; i < 25; i++)
            System.out.println( d.get(i).meaning );
    }

}
