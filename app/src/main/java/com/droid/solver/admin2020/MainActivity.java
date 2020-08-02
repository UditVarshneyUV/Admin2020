package com.droid.solver.admin2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import javax.crypto.spec.RC2ParameterSpec;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN=61;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            startActivity(new Intent(this,AdminActivity.class));
            finish();
        }

        else {

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            if (resultCode == RESULT_OK) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(MainActivity.this, "Login succesfully as admin", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,AdminActivity.class));
                finish();

            } else {
                Toast.makeText(MainActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
