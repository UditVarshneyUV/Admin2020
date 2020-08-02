package com.droid.solver.admin2020.addplaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.droid.solver.admin2020.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddPlacesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AddPlacesAdapter addPlacesAdapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        fetchData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void fetchData(){
        DatabaseReference addRef= FirebaseDatabase.getInstance().getReference("addplaces");
        addRef.addListenerForSingleValueEvent(listener);
    }

    ValueEventListener listener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<AddPlacesModel> list = new ArrayList<>();
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                String cityName=snapshot.child("cityname").getValue(String.class);
                String description=snapshot.child("description").getValue(String.class);
                String cityImage=snapshot.child("image").getValue(String.class);
                AddPlacesModel model=new AddPlacesModel(cityName, description, cityImage);
                list.add(model);
            }
            addPlacesAdapter=new AddPlacesAdapter(AddPlacesActivity.this,list);
            recyclerView.setAdapter(addPlacesAdapter);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(AddPlacesActivity.this, "Error occured in retrieving data ", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    };
}
