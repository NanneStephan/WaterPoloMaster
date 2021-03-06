package nl.nannestephan.waterpolomaster;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * saves Name of both Teams
     */

    static final String lastTeamNameOne = "Name1";
    static final String lastTeamNameTwo = "Name2";
    /**
     * saves Score of both Teams
     */
    static final String lastScoreTeamOne = "ScoreTeamA";
    static final String lastScoreTeamTwo = "ScoreTeamB";

    Dialog ThisDialog;

    CountDownTimer timer;
    CountDownTimer timerTwo;

    int scoreTeamOne = 0;
    int scoreTeamTwo = 0;

    int nameTeamOne;
    int nameTeamTwo;

    public int TimeOne;
    public int TimeTwo;

    private TextView CounterOne;
    private TextView CounterTwo;

    EditText TeamOne;
    EditText TeamTwo;

    TextView TeamOneEdit;
    TextView TeamTwoEdit;
    TextView scoreViewOne;
    TextView scoreViewTwo;

    Button saveDialog;

    /**
     * save's score and team name
     */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(lastScoreTeamOne, scoreTeamOne);
        savedInstanceState.putInt(lastScoreTeamTwo, scoreTeamTwo);

        savedInstanceState.putInt(lastTeamNameOne, nameTeamOne);
        savedInstanceState.putInt(lastTeamNameTwo, nameTeamTwo);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Restores on rotation
     */

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            scoreTeamOne = savedInstanceState.getInt(lastScoreTeamOne);
            scoreTeamTwo = savedInstanceState.getInt(lastScoreTeamTwo);

            nameTeamOne = savedInstanceState.getInt(lastTeamNameOne);
            nameTeamTwo = savedInstanceState.getInt(lastTeamNameTwo);

            scoreTeamOne(scoreTeamOne);
            scoreTeamTwo(scoreTeamTwo);

            displayForTeamOne(nameTeamOne);
            displayForTeamTwo(nameTeamTwo);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CounterOne = findViewById(R.id.timeCounterOne);
        CounterTwo = findViewById(R.id.timeCounterTwo);

        TeamOneEdit = findViewById(R.id.TeamOneEdit);
        TeamTwoEdit = findViewById(R.id.TeamTwoEdit);

        scoreViewOne = findViewById(R.id.scoreTeamOne);
        scoreViewTwo = findViewById(R.id.scoreTeamTwo);

    }

    /**
     * dialog
     */
    /**
     * button to get dialog
     */

    public void teamNamesAdd(View v) {
        ThisDialog = new Dialog(MainActivity.this);
        ThisDialog.setContentView(R.layout.dialog_team);

        TeamOne = ThisDialog.findViewById(R.id.addTeamNameOne);
        TeamTwo = ThisDialog.findViewById(R.id.addTeamNameTwo);
        saveDialog = ThisDialog.findViewById(R.id.saveDialog);

        TeamOne.setEnabled(true);
        TeamTwo.setEnabled(true);
        saveDialog.setEnabled(true);

        ThisDialog.show();
    }


    /**
     * save's name + add name's to TextView
     */

    public void saveDialog(View v) {
        SharedPrefTeamOne(TeamOne.getText().toString());
        SharedPrefTeamTwo(TeamTwo.getText().toString());

        SharedPreferences TeamName = getApplicationContext().getSharedPreferences("NAME", 0);

        TeamOneEdit.setText(TeamName.getString("TeamOne", null));
        TeamTwoEdit.setText(TeamName.getString("TeamTwo", null));

        displayForTeamTwo(nameTeamOne);
        displayForTeamOne(nameTeamTwo);

        ThisDialog.cancel();
    }

    /**
     * Team One + - buttons
     */

    public void teamOneAddOne(View view) {
        scoreTeamOne = scoreTeamOne + 1;
        scoreTeamOne(scoreTeamOne);
    }

    public void teamOneRemoveOne(View view) {
        scoreTeamOne = scoreTeamOne - 1;
        scoreTeamOne(scoreTeamOne);
    }

    /**
     * Team Two + - buttons
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
     * Timer Team One
     */

    public void StartOne(View view) {
        timer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                CounterOne.setText(String.valueOf(TimeOne));
                TimeOne++;
            }

            public void onFinish() {
                CounterOne.setText("Done");


            }
        };
        timer.start();
    }

    public void StopOne(View view) {
        timer.cancel();
    }

    public void ResetOne(View view) {
        TimeOne = 0;
        CounterOne.setText(String.valueOf(TimeOne));
    }

    /**
     * Timer Team Two
     */

    public void StartTwo(View view) {
        timerTwo = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                CounterTwo.setText(String.valueOf(TimeTwo));
                TimeTwo++;
            }

            public void onFinish() {
                CounterTwo.setText("Done");


            }
        };
        timerTwo.start();
    }

    public void StopTwo(View view) {
        timerTwo.cancel();
    }

    public void ResetTwo(View view) {
        TimeTwo = 0;
        CounterTwo.setText(R.string.FaultText);
    }

    /**
     * display team One
     */

    public void displayForTeamOne(int TeamNameOne) {
        TeamOneEdit.setText(String.valueOf(TeamNameOne));

        SharedPreferences NameView = getApplicationContext().getSharedPreferences("NAME", 0);
        TeamOneEdit.setText(NameView.getString("TeamOne", null));
    }

    /**
     * edit Team Two
     */

    public void displayForTeamTwo(int TeamNameTwo) {
        TeamTwoEdit.setText(String.valueOf(TeamNameTwo));

        SharedPreferences NameView = getApplicationContext().getSharedPreferences("NAME", 0);
        TeamTwoEdit.setText(NameView.getString("TeamTwo", null));
    }

    /**
     * shared preferences Team One
     */

    public void SharedPrefTeamOne(String TeamOne) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("NAME", 0);
        SharedPreferences.Editor prefEDIT = prefs.edit();
        prefEDIT.putString("TeamOne", TeamOne);
        prefEDIT.apply();
    }

    /**
     * shared preferences Team Two
     */

    public void SharedPrefTeamTwo(String TeamTwo) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("NAME", 0);
        SharedPreferences.Editor prefEDIT = prefs.edit();
        prefEDIT.putString("TeamTwo", TeamTwo);
        prefEDIT.apply();
    }

    /**
     * Score Display Team One
     */

    public void scoreTeamOne(int score) {
        scoreViewOne.setText(String.valueOf(score));
    }

    /**
     * score display Team Two
     */

    public void scoreTeamTwo(int score) {
        scoreViewTwo.setText(String.valueOf(score));
    }

    public void reset(View view){
        scoreTeamOne = 0;
        scoreTeamTwo = 0;
        scoreTeamOne(scoreTeamOne);
        scoreTeamTwo(scoreTeamTwo);
        TeamOneEdit.setText(R.string.TeamName);
        TeamTwoEdit.setText(R.string.TeamName);
        SharedPrefTeamOne(TeamOneEdit.getText().toString());
        SharedPrefTeamTwo(TeamTwoEdit.getText().toString());
    }
}

