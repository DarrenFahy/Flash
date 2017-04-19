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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("made it here ----------3");
        startGame();
    }


    public void startGame()
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //set title to name of button clicked
        String str = extras.getString("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(str);

        int min = 0;
        int max = MainActivity.set.size();
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1);


         if ( str.equals("Meaning"))
        {
            meaningQuiz(0);
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

    }

    //public void saveResults(int x)
//    {
//        set.get(x).pastEight[0] = 1;
//    }



    public void meaningQuiz(final int x)
    {
        System.out.println("Size of list is "+ MainActivity.set.size());

        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView test1 = (TextView) findViewById(R.id.otherTV1);
        TextView test2 = (TextView) findViewById(R.id.otherTV2);
        final TextView meaning = (TextView) findViewById(R.id.currentCard);

        final Button upper1 = (Button) findViewById(R.id.upper1);
        upper1.setBackgroundResource(android.R.drawable.btn_default);
        upper1.setEnabled(true);
        final Button upper2 = (Button) findViewById(R.id.upper2);
        upper2.setBackgroundResource(android.R.drawable.btn_default);
        upper2.setEnabled(true);
        final Button upper3 = (Button) findViewById(R.id.upper3);
        upper3.setBackgroundResource(android.R.drawable.btn_default);
        upper3.setEnabled(true);
        final Button upper4 = (Button) findViewById(R.id.upper4);
        upper4.setBackgroundResource(android.R.drawable.btn_default);
        upper4.setEnabled(true);
        final Button lower1 = (Button) findViewById(R.id.lower1);
        lower1.setBackgroundResource(android.R.drawable.btn_default);
        lower1.setEnabled(true);
        final Button lower2 = (Button) findViewById(R.id.lower2);
        lower2.setBackgroundResource(android.R.drawable.btn_default);
        lower2.setEnabled(true);
        final Button lower3 = (Button) findViewById(R.id.lower3);
        lower3.setBackgroundResource(android.R.drawable.btn_default);
        lower3.setEnabled(true);
        final Button lower4 = (Button) findViewById(R.id.lower4);
        lower4.setBackgroundResource(android.R.drawable.btn_default);
        lower4.setEnabled(true);

        test1.setText("Pinyin");
        test2.setText("Character");
        final int correctTop;
        final int correctBottom;
        Random rand1 = new Random();
        final int currentFCNumber;
        int wrong1;
        int wrong2;
        int wrong3;
        final int m = MainActivity.set.size() -1;

        currentFCNumber = x;

        meaning.setText(MainActivity.set.get(x).meaning);

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

        correctTop = rand1.nextInt(4) + 1;
        correctBottom = rand1.nextInt(4) + 1;
        FlashCard current = MainActivity.set.get(currentFCNumber);
        FlashCard wrong11 = MainActivity.set.get(wrong1);
        FlashCard wrong12 = MainActivity.set.get(wrong2);
        FlashCard wrong13 = MainActivity.set.get(wrong3);
        System.out.println(correctTop + " ppppppppppppppppppp " + correctBottom);


        if(correctTop == 1)
        {
            upper1.setText(current.getPinyin());
            upper2.setText(wrong11.getPinyin());
            upper3.setText(wrong12.getPinyin());
            upper4.setText(wrong13.getPinyin());
        }

        if(correctTop == 2)
        {
            System.out.println("in 1=====");

            upper1.setText(wrong11.getPinyin());
            upper2.setText(current.getPinyin());
            upper3.setText(wrong12.getPinyin());
            upper4.setText(wrong13.getPinyin());

        }

        if(correctTop == 3)
        {
            upper1.setText(wrong11.getPinyin());
            upper2.setText(wrong12.getPinyin());
            upper3.setText(current.getPinyin());
            upper4.setText(wrong13.getPinyin());
        }

        if(correctTop == 4)
        {
            upper1.setText(wrong11.getPinyin());
            upper2.setText(wrong12.getPinyin());
            upper3.setText(wrong13.getPinyin());
            upper4.setText(current.getPinyin());
        }

        upper1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctTop == 1)
                {
                    upper1.setBackgroundColor(Color.GREEN);
//                    set.get(currentFCNumber).results.remove();
//                    set.get(currentFCNumber).results.add(1);

                }
                else
                    upper1.setBackgroundColor(Color.RED);

                upper2.setEnabled(false);
                upper3.setEnabled(false);
                upper4.setEnabled(false);
            }
        });
        upper2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctTop == 2)
                    upper2.setBackgroundColor(Color.GREEN);
                else
                    upper2.setBackgroundColor(Color.RED);

                upper1.setEnabled(false);
                upper3.setEnabled(false);
                upper4.setEnabled(false);

            }
        });
        upper3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctTop == 3)
                    upper3.setBackgroundColor(Color.GREEN);
                else
                    upper3.setBackgroundColor(Color.RED);

                upper2.setEnabled(false);
                upper1.setEnabled(false);
                upper4.setEnabled(false);
            }
        });
        upper4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctTop == 4)
                    upper4.setBackgroundColor(Color.GREEN);
                else
                    upper4.setBackgroundColor(Color.RED);

                upper2.setEnabled(false);
                upper3.setEnabled(false);
                upper1.setEnabled(false);
            }
        });
//--------------------------------------------------------------------------------------------------


        if(correctBottom == 1)
        {
            lower1.setText(current.charString);
            lower2.setText(wrong11.charString);
            lower3.setText(wrong12.charString);
            lower4.setText(wrong13.charString);
        }
        if(correctBottom == 2)
        {
            lower1.setText(wrong11.charString);
            lower2.setText(current.charString);
            lower3.setText(wrong12.charString);
            lower4.setText(wrong13.charString);

        }

        if(correctBottom == 3)
        {
            lower1.setText(wrong11.charString);
            lower2.setText(wrong12.charString);
            lower3.setText(current.charString);
            lower4.setText(wrong13.charString);
        }

        if(correctBottom == 4)
        {
            lower1.setText(wrong11.charString);
            lower2.setText(wrong12.charString);
            lower3.setText(wrong13.charString);
            lower4.setText(current.charString);
        }// end correct button 4

        lower1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctBottom == 1)
                    lower1.setBackgroundColor(Color.GREEN);
                else
                    lower1.setBackgroundColor(Color.RED);

                lower2.setEnabled(false);
                lower3.setEnabled(false);
                lower4.setEnabled(false);
            }
        });

        lower2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctBottom == 2)
                    lower2.setBackgroundColor(Color.GREEN);
                else
                    lower2.setBackgroundColor(Color.RED);

                lower1.setEnabled(false);
                lower3.setEnabled(false);
                lower4.setEnabled(false);
            }
        });

        lower3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctBottom == 3)
                    lower3.setBackgroundColor(Color.GREEN);
                else
                    lower3.setBackgroundColor(Color.RED);

                lower2.setEnabled(false);
                lower1.setEnabled(false);
                lower4.setEnabled(false);
            }
        });
        lower4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctBottom == 4)
                    lower4.setBackgroundColor(Color.GREEN);
                else
                    lower4.setBackgroundColor(Color.RED);

                lower2.setEnabled(false);
                lower3.setEnabled(false);
                lower1.setEnabled(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {

               // if()

                if((!upper1.isEnabled() || !upper2.isEnabled() || !upper3.isEnabled() ||!upper4.isEnabled()))
                    if(!lower1.isEnabled() || !lower2.isEnabled() || !lower3.isEnabled() || !lower4.isEnabled())
                    {
                        if (x >= MainActivity.set.size() -2 )
                        {
                            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                            startActivity(intent);
                        }
                        else
                            meaningQuiz(x+1);
                    }

            }//end on click
        });
    }



}
