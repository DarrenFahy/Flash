package cosc150.flash;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
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
import java.util.Random;
import java.util.Vector;
import android.graphics.Color;


public class Main2Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startGame();
    }

    public void startGame()
    {
//        System.out.println("load file function");
//        loadFile();

        Vector<FlashCard> fcards = manualCards();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //set title to name of button clicked
        String str = extras.getString("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(str);
       // getWindow().setTitle(title.toString());

        int min = 0;
        int max = fcards.size();
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1);


         if ( str.equals("Meaning"))
        {

            meaningQuiz(extras.getInt("number_cards"));
          /*  test1.setText("Pinyin");
            upper1.setText("test");
            upper2.setText("test");
            upper3.setText("test");
            upper4.setText("test");

            test2.setText("Character");
            lower1.setText("test");
            lower2.setText("test");
            lower3.setText("test");
            lower4.setText("test");*/
        }
     /*   else if (str.equals("Pinyin"))
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
*/

        //Specify type of quiz based on switch
        Boolean type = extras.getBoolean("game_mode");
        String txt = extras.getString("text");

        //number of cards to test
        int numCards = extras.getInt("number_cards");

        System.out.println( "Game mode is: " + type);
        System.out.println( "And number of cards is: " + numCards + " and message: " + str);

        //loadFile();

        System.out.println( "Tried my best to print cards");


    }

    public void importCards()
    {
        View v;
        Thread t= new Thread(
                new Runnable ()
                {

                    @Override
                    public void run()
                    {
                        try
                        {
                            // Create a URL for the desired page
                            URL url = new URL("http://people.cs.georgetown.edu/~bk620/chidi.txt");
                            // Read all the text returned by the server
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            String str;
                            System.out.println("made it here!   1");

                            Vector<FlashCard> cardList = new Vector<FlashCard>();

                            while ((str = in.readLine()) != null)
                            {
                                List<String> fclist = Arrays.asList(str.split(","));
//                        System.out.println("Symbol "+fclist.get(0));
//                        System.out.println("Pinyin "+fclist.get(1));
//                        System.out.println("Added meaning "+fclist.get(2));

                                FlashCard fc = new FlashCard( fclist.get(0), fclist.get(1), fclist.get(2) );

                                cardList.add(fc);
                                System.out.println("Added meaning "+fclist.get(2));

                            }//end while
                            //work(v);
                            System.out.println("made it here!   2");
                            printFC(cardList);
                            in.close();
                        }//end try

                        catch (Exception e) {Log.i("MA.L----->", "error=" + e);}

                    }//end run()
                }//end runable()
        );//end thead

    }//end import cards

    public void newCard()
    {

    }
    public Vector <FlashCard> manualCards()
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

        return cardList1;
    }


    public void printFC(List<FlashCard> d)
    {
        for (int i = 0; i < 25; i++)
            System.out.println( d.get(i).meaning );
    }

    public void meaningQuiz(int m)
    {
        TextView test1 = (TextView) findViewById(R.id.otherTV1);
        TextView test2 = (TextView) findViewById(R.id.otherTV2);
        final Button upper1 = (Button) findViewById(R.id.upper1);
        final Button upper2 = (Button) findViewById(R.id.upper2);
        final Button upper3 = (Button) findViewById(R.id.upper3);
        final Button upper4 = (Button) findViewById(R.id.upper4);
        final Button lower1 = (Button) findViewById(R.id.lower1);
        final Button lower2 = (Button) findViewById(R.id.lower2);
        final Button lower3 = (Button) findViewById(R.id.lower3);
        final Button lower4 = (Button) findViewById(R.id.lower4);
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        test1.setText("Pinyin");
        test2.setText("Character");
        int correctTop;
        int correctBottom;
        Random rand1 = new Random();
        int currentFCNumber;
        int wrong1;
        int wrong2;
        int wrong3;
        Vector <FlashCard> set = manualCards();

        for(int i = 0; i < m; i++)
        {
            currentFCNumber = rand1.nextInt(8) + 0;

            do
            {
                wrong1 = rand1.nextInt(8) + 0;
            }
            while(wrong1 == currentFCNumber);

            do
            {
                wrong2 = rand1.nextInt(8) + 0;
            }
            while((wrong2 == currentFCNumber)||(wrong2 == wrong1));

            do
            {
                wrong3 = rand1.nextInt(8) + 0;
            }
            while((wrong3 == currentFCNumber)||(wrong3 == wrong1) ||(wrong3 == wrong2));

            correctTop = rand1.nextInt(4) + 1;;
            correctBottom = rand1.nextInt(4) + 1;
            FlashCard current = set.get(currentFCNumber);
            FlashCard wrong11 = set.get(wrong1);
            FlashCard wrong12 = set.get(wrong2);
            FlashCard wrong13 = set.get(wrong3);
           System.out.println(correctTop + " ppppppppppppppppppp " + correctBottom);


            if(correctTop == 1)
            {
                upper1.setText(current.getPinyin());
                upper2.setText(wrong11.getPinyin());
                upper3.setText(wrong12.getPinyin());
                upper4.setText(wrong13.getPinyin());

                upper1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper1.setBackgroundColor(Color.GREEN);
                        upper2.setEnabled(false);
                        upper3.setEnabled(false);
                        upper4.setEnabled(false);
                    }
                });

            }

            if(correctTop == 2)
            {
                System.out.println("in 1=====");

                upper1.setText(wrong11.getPinyin());
                upper2.setText(current.getPinyin());
                upper3.setText(wrong12.getPinyin());
                upper4.setText(wrong13.getPinyin());

                upper2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper2.setBackgroundColor(Color.GREEN);
                        upper1.setEnabled(false);
                        upper3.setEnabled(false);
                        upper4.setEnabled(false);

                    }
                });

            }

            if(correctTop == 3)
            {
                upper1.setText(wrong11.getPinyin());
                upper2.setText(wrong12.getPinyin());
                upper3.setText(current.getPinyin());
                upper4.setText(wrong13.getPinyin());

                upper3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper3.setBackgroundColor(Color.GREEN);
                        upper2.setEnabled(false);
                        upper1.setEnabled(false);
                        upper4.setEnabled(false);
                    }
                });
            }

            if(correctTop == 4)
            {
                upper1.setText(wrong11.getPinyin());
                upper2.setText(wrong12.getPinyin());
                upper3.setText(wrong13.getPinyin());
                upper4.setText(current.getPinyin());

                upper4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper4.setBackgroundColor(Color.GREEN);
                        upper2.setEnabled(false);
                        upper3.setEnabled(false);
                        upper1.setEnabled(false);
                    }
                });
            }
//--------------------------------------------------------------------------------------------------


            if(correctBottom == 1)
            {
                lower1.setText(current.charString);
                lower2.setText(wrong11.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);

                lower1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        lower1.setBackgroundColor(Color.GREEN);
                        lower2.setEnabled(false);
                        lower3.setEnabled(false);
                        lower4.setEnabled(false);
                    }
                });
            }
            if(correctBottom == 2)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(current.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);

                lower2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        lower2.setBackgroundColor(Color.GREEN);
                        lower1.setEnabled(false);
                        lower3.setEnabled(false);
                        lower4.setEnabled(false);
                    }
                });
            }

            if(correctBottom == 3)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(current.charString);
                lower4.setText(wrong13.charString);

                lower3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        lower3.setBackgroundColor(Color.GREEN);
                        lower2.setEnabled(false);
                        lower1.setEnabled(false);
                        lower4.setEnabled(false);
                    }
                });
            }

            if(correctBottom == 4)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(wrong13.charString);
                lower4.setText(current.charString);

                lower4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        lower4.setBackgroundColor(Color.GREEN);
                        lower2.setEnabled(false);
                        lower3.setEnabled(false);
                        lower1.setEnabled(false);
                    }
                });
            }



        }



    }

}
