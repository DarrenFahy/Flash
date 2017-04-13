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
        //System.out.println(str);
        quizTitle();

    }

    public void quizTitle( )
    {
        Intent intent = getIntent();
        String str = intent.getStringExtra("game_title");
        TextView title = (TextView) findViewById(R.id.textView5);
        title.setText(str);
    }
    public void gameType()
    {
        Intent intent2 = getIntent();
        Boolean type = intent2.getBooleanExtra("game_mode");

    }
}
