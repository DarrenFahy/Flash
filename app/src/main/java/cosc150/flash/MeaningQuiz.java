package cosc150.flash;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Darren on 4/17/17.
 */

public class MeaningQuiz extends AppCompatActivity
{
    public void meaningQuiz( int m)
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

        Vector<FlashCard> set = manualCards();

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
            // System.out.println(correctBottom + "kkkkkkkkk " +correctTop+" kkkkkkkkkkkkkkkkkk");
            FlashCard current = set.get(currentFCNumber);
            FlashCard wrong11 = set.get(wrong1);
            FlashCard wrong12 = set.get(wrong2);
            FlashCard wrong13 = set.get(wrong3);
            System.out.println(correctTop + "ppppppppppppppppppp");
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
                    }
                });
/*

                upper2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper2.setBackgroundColor(Color.RED);
                    }
                });


                upper3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper3.setBackgroundColor(Color.RED);
                    }
                });

                upper4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper4.setBackgroundColor(Color.RED);
                    }
                });

                upper2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper2.setBackgroundColor(Color.RED);
                    }
                });

                upper3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper3.setBackgroundColor(Color.RED);
                    }
                });
                upper4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper4.setBackgroundColor(Color.RED);
                    }
                });
*/
            }

            if(correctTop == 2)
            {
                System.out.println("in 1=====");

                upper1.setText(wrong11.getPinyin());
                upper2.setText(current.getPinyin());
                upper3.setText(wrong12.getPinyin());
                upper4.setText(wrong13.getPinyin());

/*
                upper1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper1.setBackgroundColor(Color.RED);
                    }
                });
*/

                upper2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper2.setBackgroundColor(Color.GREEN);
                        nextButton.setVisibility(1);
                    }
                });

/*
                upper3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper3.setBackgroundColor(Color.RED);
                    }
                });

                upper4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper4.setBackgroundColor(Color.RED);
                    }
                });

                upper2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper2.setBackgroundColor(Color.RED);
                    }
                });

                upper3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper3.setBackgroundColor(Color.RED);
                    }
                });
                upper4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        upper4.setBackgroundColor(Color.RED);
                    }
                });
*/
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
                    }
                });
            }
//--------------------------------------------------------------------------------------------------


            if(correctBottom == 0)
            {
                lower1.setText(current.charString);
                lower2.setText(wrong11.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);
            }
            if(correctBottom == 1)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(current.charString);
                lower3.setText(wrong12.charString);
                lower4.setText(wrong13.charString);
            }

            if(correctBottom == 2)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(current.charString);
                lower4.setText(wrong13.charString);
            }

            if(correctBottom == 3)
            {
                lower1.setText(wrong11.charString);
                lower2.setText(wrong12.charString);
                lower3.setText(wrong13.charString);
                lower4.setText(current.charString);
            }



        }

    }//end meaning quiz

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
    }//end manual cards
}

