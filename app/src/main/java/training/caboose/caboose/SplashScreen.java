package training.caboose.caboose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Splash Screen class adds in a splash screen while the app loads
 */
public class SplashScreen extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth.signOut();
        SharedPreferences preferences =
                getApplicationContext()
                        .getSharedPreferences(getString(R.string.userSharedPrefs),0);
        preferences.edit().clear().commit();
    }

    /** Called when the user taps the Send button */
    public void openLogin(View view) {
        Intent intent = new Intent(this, AppLogin.class);
        startActivity(intent);
    }


}
