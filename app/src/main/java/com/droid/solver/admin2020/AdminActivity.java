package com.droid.solver.admin2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.droid.solver.admin2020.addplaces.AddPlacesActivity;
import com.droid.solver.admin2020.suggestion.SuggestionActivity;
import com.droid.solver.admin2020.suggestion.SuggestionAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AdminActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private Toolbar toolbar;
    private CardView suggestionCard,addPlacesCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        suggestionCard=findViewById(R.id.suggestion_card);
        addPlacesCard=findViewById(R.id.add_places_card);
        toolbar.setOnMenuItemClickListener(this);
        suggestionCard.setOnClickListener(this);
        addPlacesCard.setOnClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.logout){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(AdminActivity.this,MainActivity.class));
                            finish();
                        }
                    });
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.suggestion_card:
                startActivity(new Intent(AdminActivity.this, SuggestionActivity.class));
                break;
            case R.id.add_places_card:
                startActivity(new Intent(AdminActivity.this, AddPlacesActivity.class));
                break;
        }
    }
    private void showMessage(String text){
        Toast.makeText(this,text+" is clicked", Toast.LENGTH_SHORT).show();

    }
}
