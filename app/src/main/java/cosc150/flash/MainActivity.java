package cosc150.flash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class MainActivity extends AppCompatActivity
{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String dictionaryFileName = "flashcardresults.txt";
    static Vector<FlashCard> set = new Vector<FlashCard>();
    public static long beginTime;
    public static long endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCards();
    }

    public void loadCards()
    {
/*
        String ff = "";
        try
        {
            FileInputStream fis = openFileInput("flashcardresults.txt");

            int count=100;
            int cint;
            byte[] bbuf = new byte[50];
            fis.read(bbuf, 0, 20 );
            ff = new String(bbuf);
            fis.close();
        }
        catch(Exception e) {System.out.println("oooops");}
       // EditText field1 = (EditText) findViewById(R.id.field1);

*/

        //System.out.println("in import cards");
        View v;
        Thread t = new Thread(
                new Runnable() {

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

                            while ((str = in.readLine()) != null) {
                                List<String> fclist = Arrays.asList(str.split(","));
                                System.out.println("Symbol " + fclist.get(0));
                                System.out.println("Pinyin " + fclist.get(1));
                                System.out.println("Added meaning " + fclist.get(2));
                                FlashCard fc = new FlashCard(fclist.get(0), fclist.get(1), fclist.get(2));

                                set.add(fc);
                                //System.out.println("Size of list is "+set.size());

                            }//end while

                            System.out.println("made it here!   2");
                            in.close();
                            System.out.println("Size of list is after close is: " + set.size());
                        }//end try

                        catch (Exception e) {
                            Log.i("MA.L----->", "error=" + e);
                        }

                    }//end run()
                }//end runable()
        );
        //System.out.println("made it here????????");
        t.start();//end thead
    }


    public void beginQuiz(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle extras = new Bundle();
        Button srcButton;

        Switch ModeSwitch = (Switch) findViewById(R.id.ModeSwitch);
        Boolean switchState = ModeSwitch.isChecked();

        EditText cardNum = (EditText) findViewById(R.id.editText2);
        String no = cardNum.getText().toString();
        Main2Activity.numCards = Integer.parseInt(no);


        switch (view.getId())
        {
            case R.id.button1:
                srcButton = (Button) findViewById(R.id.button1);
                break;
            case R.id.button2:
                srcButton = (Button) findViewById(R.id.button2);
                break;
            case R.id.button3:
                srcButton = (Button) findViewById(R.id.button3);
                break;
            case R.id.button4:
                srcButton = (Button) findViewById(R.id.button4);
                break;
            default:
                throw new IllegalArgumentException("shit... " );
        }

        beginTime = System.currentTimeMillis();
        String message = srcButton.getText().toString();

        extras.putString("game_title", message);
        extras.putBoolean("game_mode", switchState);
        extras.putInt("number_cards", Main2Activity.numCards);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
