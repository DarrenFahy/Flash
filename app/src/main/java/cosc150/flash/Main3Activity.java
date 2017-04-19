package cosc150.flash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        results();
    }

    public void results()
    {
        TextView result = (TextView) findViewById(R.id.textView6);
        int correct=0;
        for (int i=0; i < Main2Activity.numCards; i++)
        {
            if (MainActivity.set.get(i).quizUpperCorrect == true && MainActivity.set.get(i).quizLowerCorrect == true)
            {
                correct = correct+1;
                MainActivity.set.get(i).updatePastEight(1);
            }
            else
            {
                MainActivity.set.get(i).updatePastEight(0);
            }
            MainActivity.set.get(i).quizUpperCorrect = false;
            MainActivity.set.get(i).quizLowerCorrect = false;
        }
        result.setText(correct + " out of " + Main2Activity.numCards );
        for (int i=0; i < MainActivity.set.size()-1; i++)
        {
            for (int x = 0; x < 8; x++)
            {
                System.out.print("Past 8? "+MainActivity.set.get(i).pastEight.get(x));
            }
            System.out.print(" ");
        }
    }

}
