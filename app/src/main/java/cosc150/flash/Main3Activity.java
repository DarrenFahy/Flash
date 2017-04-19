package cosc150.flash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static java.sql.Types.NULL;

public class Main3Activity extends AppCompatActivity
{
    public File file = new File(MainActivity.dictionaryFileName);

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
        Long gameTime;
        long minutes;
        long seconds;
        TextView result = (TextView) findViewById(R.id.textView6);
        TextView timeTV = (TextView) findViewById(R.id.timeTV);

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

        //time in seconds
        gameTime = (MainActivity.endTime - MainActivity.beginTime)/ 1000;
        seconds = gameTime % 60;
        minutes = gameTime / 60;


        timeTV.setText("Quiz took " + minutes + " minutes and " + seconds + " seconds to complete");
        result.setText(correct + " out of " + Main2Activity.numCards );

        for (int i=0; i < Main2Activity.numCards; i++)
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


        try
        {

            //System.out.println("In try.");
            FileOutputStream fos = openFileOutput(MainActivity.dictionaryFileName, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //For testing purposes to read in from the file we just wrote to.
            FileInputStream fis = openFileInput(MainActivity.dictionaryFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (int i = 0; i < Main2Activity.numCards; i++)
            {
                //write stuff to file
                oos.writeObject(MainActivity.set.get(i));//tergetBytes());

                //for testing purposes. Works corectly
                FlashCard test = (FlashCard) ois.readObject();
                System.out.println("Test results:     " +test.getMeaning());

            }
            fos.close();

//            FileInputStream fis = openFileInput(MainActivity.dictionaryFileName);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            while(!ois.readObject().equals(NULL))
//            {
//                Object obj = ois.readObject();
//                System.out.print("IT WORKED!     "+(obj));
//            }


           // fis.close();

        } catch(Exception e) {System.out.println(e+ "ooops-------------");}

    }//end saveResults

    public void newGame(View view)
    {
        //sends it to main activity 1 & resets variables
        Main2Activity.numCards = 0;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
