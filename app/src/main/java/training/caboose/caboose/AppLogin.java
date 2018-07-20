package training.caboose.caboose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import training.caboose.caboose.Models.EmployeeInfo;

/**
 * AppLogin class defines the activity where the employee will login using the unique code
 * provided by the employer
 */

public class AppLogin extends AppCompatActivity {

    FirebaseAuth mAuth;

    /**
     * If login works, show toast and login to view given modules and positions
     * @param view Current view
     */
    public void tryLogin(View view) {
        Toast.makeText(AppLogin.this, "Signing in to Caboose",
                Toast.LENGTH_SHORT).show();
        EditText userLoginCode = /*(EditText)*/ findViewById(R.id.userLoginCode);
        ProgressBar progressBar = /*(ProgressBar)*/ findViewById(R.id.loginSpinner);
        new getLoginToken(progressBar).execute(userLoginCode.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_app_login);

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //User already logged in
            startActivity(new Intent(this, ViewPositions.class));
        }
    }


    private class getLoginToken extends AsyncTask<String, Boolean, EmployeeInfo> {

        private ProgressBar progressBar;
        public getLoginToken(ProgressBar progressBar){
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute(){
            this.progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected EmployeeInfo doInBackground(String... string) {
            EmployeeInfo thisEmployee = new EmployeeInfo();
            thisEmployee.setAuthToken("INVALID");

            try {
                String postData = "{\"employeeLoginId\":\"" + string[0] + "\"}";
                URL url = new URL(getString(R.string.loginServiceUrl));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Content-Length", "" + Integer.toString(postData.getBytes().length));
                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);


                byte[] outputInBytes = postData.getBytes("UTF-8");
                OutputStream os = urlConnection.getOutputStream();
                os.write(outputInBytes);
                os.close();


                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject authResponse = new JSONObject(builder.toString());
                if (!authResponse.has("Error")) {
                    thisEmployee.setAuthToken(authResponse.getString("token"));
                    thisEmployee.setOrgId(authResponse.getString("orgId"));
                    thisEmployee.setEmail(authResponse.getString("email"));
                    thisEmployee.setName(authResponse.getString("name"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return thisEmployee;
        }

        @Override
        protected void onPostExecute(final EmployeeInfo thisEmployee) {
            this.progressBar.setVisibility(View.INVISIBLE);
            if (thisEmployee.getAuthToken().equals("INVALID")) {
                Toast.makeText(AppLogin.this, "Login code not found",
                        Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithCustomToken(thisEmployee.getAuthToken())
                        .addOnCompleteListener(AppLogin.this , new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.w("App_Login", "signInWithCustomToken:success");

                                    SharedPreferences preferences =
                                            getApplicationContext()
                                                    .getSharedPreferences(getString(R.string.userSharedPrefs),0);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("orgId", thisEmployee.getOrgId());
                                    editor.putString("email", thisEmployee.getEmail());
                                    editor.putString("name", thisEmployee.getName());
                                    editor.apply();

                                    startActivity(new Intent(AppLogin.this, ViewPositions.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w("App_Login", "signInWithCustomToken:failure", task.getException());
                                    Toast.makeText(AppLogin.this, "Unable to login",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        }
    }

}

