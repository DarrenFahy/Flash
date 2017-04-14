package cosc150.flash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    View v;
    Thread t= new Thread(
        new Runnable ()
        {
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


    public void printFC(List<FlashCard> d)
    {
        for (int i = 0; i < 25; i++)
            System.out.println( d.get(i).meaning );
    }

    public void beginQuiz(View view)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        Bundle extras = new Bundle();
        Button srcButton;

        Switch ModeSwitch = (Switch) findViewById(R.id.ModeSwitch);
        Boolean switchState = ModeSwitch.isChecked();

        intent.putExtra ( "text", findViewById(editText2).getText().toString() );

        //EditText cardNum = (EditText) findViewById(editText2);
        String no = cardNum.getText().toString();
        //String text = edit_text.getText.toString;
        int numCards = Integer.parseInt(no);


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

        String message = srcButton.getText().toString();

        extras.putString("game_title", message);
        extras.putBoolean("game_mode", switchState);
        extras.putInt("number_cards", numCards);
        startActivity(intent);
    }

}
