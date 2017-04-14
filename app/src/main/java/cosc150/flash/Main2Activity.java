package cosc150.flash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //set title to name of button clicked
        String str = extras.getString("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(str);

        //Specify type of quiz based on switch
        Boolean type = extras.getBoolean("game_mode");

        //number of cards to test
        int numCards = extras.getInt("number_cards");

        System.out.println( "Game mode is: " + type);
        System.out.println( "And number of cards is: " + numCards);



    }

}
