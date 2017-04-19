package cosc150.flash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main3Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        results();
        saveResults();
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
            System.out.print("Past 8:       ");

            for (int x = 0; x < 8; x++)
            {
                System.out.print(MainActivity.set.get(i).pastEight.get(x));
            }
            System.out.println();
        }
    }

    public void saveResults()
    {
        String filePath = getFilesDir().getPath() + MainActivity.dictionaryFileName;
        System.out.println("In save results.");
        File file = new File(filePath);
        try
        {
            System.out.println("In try.");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (int i = 0; i < Main2Activity.numCards; i++)
            {
                //write stuff to file
                oos.write (MainActivity.set.get(i).character );
            }
            fos.close();
        }
        catch (Exception e)
        {
            System.out.println("Error caught saving results");
        }

    }

}
