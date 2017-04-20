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

    static int numCards;
    static String quizType;
    static int currentFCNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startGame();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("key--paused-----","-------message------pause");
    }

    public void startGame()
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //set title to name of button clicked
        quizType = extras.getString("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(quizType);

        int min = 0;
        int max = MainActivity.set.size();
        Random r = new Random();

        //number of cards to test...made it static so pasing with an intent is useless. Oh well
        numCards = extras.getInt("number_cards");

        //starts quiz at array number 0
        Quiz(0);

    }//end startGame()



    public void Quiz(final int x)
    {
        currentFCNumber = x;

        //if it's blacklisted, go to the next card
        if (MainActivity.set.get(x).blackList == true)
        {
            if (currentFCNumber >= MainActivity.set.size() -2 || currentFCNumber >= numCards-1 )
            {
                MainActivity.endTime = System.currentTimeMillis();
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                //intent.putExtra
                startActivity(intent);
            }
            else
            {
                numCards+=1;
                Quiz(currentFCNumber + 1);
            }
        }

        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView test1 = (TextView) findViewById(R.id.otherTV1);
        TextView test2 = (TextView) findViewById(R.id.otherTV2);
        final TextView currentCard = (TextView) findViewById(R.id.currentCard);

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

        final int correctTop;
        final int correctBottom;
        Random rand1 = new Random();
        int wrong1;
        int wrong2;
        int wrong3;
        boolean top = false;
        boolean bottom= false;
        final int m = MainActivity.set.size() -1;


        if (quizType.equals("Meaning") )
        {
            FlashCard.lastMeaningDate  = System.currentTimeMillis();
            test1.setText("Pinyin");
            test2.setText("Character");
            currentCard.setText(MainActivity.set.get(x).meaning);
        }
        else if (quizType.equals("Pinyin") )
        {
            FlashCard.lastPinyinDate  = System.currentTimeMillis();
            test1.setText("Meaning");
            test2.setText("Character");
            currentCard.setText(MainActivity.set.get(x).pinyin);
        }
        else if (quizType.equals("Character") )
        {
            FlashCard.lastCharacterDate  = System.currentTimeMillis();
            test1.setText("Meaning");
            test2.setText("Pinyin");
            currentCard.setText(MainActivity.set.get(x).charString);
        }
        else if (quizType.equals("Random") )
        {
            test1.setText("Meaning");
            test2.setText("Character");
            currentCard.setText(MainActivity.set.get(x).pinyin);
        }


        do
        {
            wrong1 = rand1.nextInt(m);
        }
        while(wrong1 == currentFCNumber);

        do
        {
            wrong2 = rand1.nextInt(m);
        }
        while((wrong2 == currentFCNumber)||(wrong2 == wrong1));

        do
        {
            wrong3 = rand1.nextInt(m);
        }
        while((wrong3 == currentFCNumber)||(wrong3 == wrong1) ||(wrong3 == wrong2));

        correctTop = rand1.nextInt(4) + 1;
        correctBottom = rand1.nextInt(4) + 1;
        FlashCard current = MainActivity.set.get(currentFCNumber);
        FlashCard wrong11 = MainActivity.set.get(wrong1);
        FlashCard wrong12 = MainActivity.set.get(wrong2);
        FlashCard wrong13 = MainActivity.set.get(wrong3);


        if(correctTop == 1)
        {
            if (quizType.equals("Meaning"))
            {
                upper1.setText(current.getPinyin());
                upper2.setText(wrong11.getPinyin());
                upper3.setText(wrong12.getPinyin());
                upper4.setText(wrong13.getPinyin());
            }
            else
            {
                upper1.setText(current.getMeaning());
                upper2.setText(wrong11.getMeaning());
                upper3.setText(wrong12.getMeaning());
                upper4.setText(wrong13.getMeaning());
            }

        }

        if(correctTop == 2)
        {
            if (quizType.equals("Meaning"))
            {
                upper1.setText(wrong11.getPinyin());
                upper2.setText(current.getPinyin());
                upper3.setText(wrong12.getPinyin());
                upper4.setText(wrong13.getPinyin());
            }
            else
            {
                upper1.setText(wrong11.getMeaning());
                upper2.setText(current.getMeaning());
                upper3.setText(wrong12.getMeaning());
                upper4.setText(wrong13.getMeaning());
            }
        }

        if(correctTop == 3)
        {
            if (quizType.equals("Meaning"))
            {
                upper1.setText(wrong11.getPinyin());
                upper2.setText(wrong12.getPinyin());
                upper3.setText(current.getPinyin());
                upper4.setText(wrong13.getPinyin());
            }
            else
            {
                upper1.setText(wrong11.getMeaning());
                upper2.setText(wrong12.getMeaning());
                upper3.setText(current.getMeaning());
                upper4.setText(wrong13.getMeaning());
            }
        }

        if(correctTop == 4)
        {
            if (quizType.equals("Meaning"))
            {
                upper1.setText(wrong11.getPinyin());
                upper2.setText(wrong12.getPinyin());
                upper3.setText(wrong13.getPinyin());
                upper4.setText(current.getPinyin());
            }
            else
            {
                upper1.setText(wrong11.getMeaning());
                upper2.setText(wrong12.getMeaning());
                upper3.setText(wrong13.getMeaning());
                upper4.setText(current.getMeaning());
            }
        }

        upper1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctTop == 1)
                {
                    upper1.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizUpperCorrect = true;
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
                if (correctTop == 2) {
                    upper2.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizUpperCorrect = true;
                }
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
                if (correctTop == 3) {
                    upper3.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizUpperCorrect = true;
                }
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
                {
                    upper4.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizUpperCorrect = true;
                }
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
            if (quizType.equals("Character"))
            {
                lower1.setText(current.pinyin);
                lower2.setText(wrong11.pinyin);
                lower3.setText(wrong12.pinyin);
                lower4.setText(wrong13.pinyin);
            }
            else
            {
                lower1.setText(current.charString);
                lower2.setText(wrong11.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);
            }
        }
        if(correctBottom == 2)
        {
            if (quizType.equals("Character"))
            {
                lower1.setText(wrong11.pinyin);
                lower2.setText(current.pinyin);
                lower3.setText(wrong12.pinyin);
                lower4.setText(wrong13.pinyin);
            }
            else
            {
                lower1.setText(wrong11.charString);
                lower2.setText(current.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);
            }

        }
        if(correctBottom == 3)
        {
            if (quizType.equals("Character"))
            {
                lower1.setText(wrong11.pinyin);
                lower2.setText(wrong12.pinyin);
                lower3.setText(current.pinyin);
                lower4.setText(wrong13.pinyin);
            }
            else
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(current.charString);
                lower4.setText(wrong13.charString);
            }
        }
        if(correctBottom == 4)
        {
            if (quizType.equals("Character"))
            {
                lower1.setText(wrong11.pinyin);
                lower2.setText(wrong12.pinyin);
                lower3.setText(wrong13.pinyin);
                lower4.setText(current.pinyin);
            }
            else
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(wrong13.charString);
                lower4.setText(current.charString);
            }
        }// end correct button 4

        lower1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if (correctBottom == 1)
                {
                    lower1.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizLowerCorrect = true;
                }
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
                {
                    lower2.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizLowerCorrect = true;
                }
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
                {
                    lower3.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizLowerCorrect = true;
                }
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
                {
                    lower4.setBackgroundColor(Color.GREEN);
                    MainActivity.set.get(currentFCNumber).quizLowerCorrect = true;
                }
                else
                    lower4.setBackgroundColor(Color.RED);

                lower2.setEnabled(false);
                lower3.setEnabled(false);
                lower1.setEnabled(false);
            }
        });


        //only enabled after they select cards (or blacklist the card)
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                if((!upper1.isEnabled() || !upper2.isEnabled() || !upper3.isEnabled() ||!upper4.isEnabled()))
                    if(!lower1.isEnabled() || !lower2.isEnabled() || !lower3.isEnabled() || !lower4.isEnabled())
                        nextCard(arg0);
            }
        });

    }


    public void blackList(View view)
    {
        //System.out.println("")
        numCards+=1;
        MainActivity.set.get(currentFCNumber).blackList = true;
        nextCard(view);
    }

    public void nextCard(View view)
    {
        if (currentFCNumber >= MainActivity.set.size() -2 || currentFCNumber >= numCards-1 )
        {
            MainActivity.endTime = System.currentTimeMillis();
            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
            //intent.putExtra
            startActivity(intent);
        }
        else
            Quiz(currentFCNumber+1);
    }

}
