package nl.nannestephan.waterpolomaster;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatus;
    private LinearLayout singOutAndDisconnect;
    private RelativeLayout singInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mStatus = findViewById(R.id.status);
        singOutAndDisconnect = findViewById(R.id.sign_out_and_disconnect);
        singInButton = findViewById(R.id.sign_in_button);

    }

    @Override
    public void onStart() {
        super.onStart();
        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        // [END on_start_sign_in]

    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
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

    }

    public void saveDialog(View view) {
        setContentView(R.layout.activity_main);

    }


    /**
     * Team A buttons
     */
    public void teamOneAddOne(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

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

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public void signIn(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public void signOut(View view) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        updateUI(null);

                    }
                });
    }

    public void disconnect(View view) {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        updateUI(null);
                    }
                });
    }


    public void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            mStatus.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
            singInButton.setVisibility(View.GONE);
            singOutAndDisconnect.setVisibility(View.VISIBLE);

        } else {
            mStatus.setText(R.string.signed_out);
            singInButton.setVisibility(View.VISIBLE);
            singOutAndDisconnect.setVisibility(View.GONE);

        }


    }


}

