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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




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

        EditText cardNum = (EditText) findViewById(R.id.editText2);
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

        System.out.println("message in MA is: " + message);

        extras.putString("game_title", message);
        extras.putBoolean("game_mode", switchState);
        extras.putInt("number_cards", numCards);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
