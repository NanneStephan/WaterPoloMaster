package nl.nannestephan.waterpolomaster;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    Dialog ThisDialog;
    int scoreTeamOneView = 0;
    int scoreTeamTwoView = 0;
    int scoreTeamOne = 0;
    int scoreTeamTwo = 0;

    /**
     * loginDialog
     */


    public void login(View view) {
        setContentView(R.layout.singindialog);
        Intent login = new Intent(MainActivity.this,
                LoginActivity.class);
        startActivity(login);

    }


    /**
     * Team A buttons
     */
    public void teamOneAddOne(View view) {


    }

    public void teamOneRemoveOne(View view) {
        scoreTeamOne = scoreTeamOne - 1;
        scoreTeamOne(scoreTeamOne);
    }

    /**
     * Team B buttons
     */

    public void teamTwoAddOne(View view) {
        scoreTeamTwo = scoreTeamTwo + 1;
        scoreTeamTwo(scoreTeamTwo);
    }

    public void teamTwoRemoveOne(View view) {
        scoreTeamTwo = scoreTeamTwo - 1;
        scoreTeamTwo(scoreTeamTwo);
    }

    /**
     * Shows Score Team A & B
     */

    public void scoreTeamTwo(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamTwo);
        scoreView.setText(String.valueOf(score));
    }

    public void scoreTeamOne(int score) {
        TextView scoreView = findViewById(R.id.scoreTeamOne);
        scoreView.setText(String.valueOf(score));
    }
}

