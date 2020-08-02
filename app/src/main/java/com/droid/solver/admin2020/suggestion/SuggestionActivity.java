package com.droid.solver.admin2020.suggestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.droid.solver.admin2020.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class SuggestionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private  SuggestionAdapter adapter;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        recyclerView=findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        fetchData();
    }
    public void fetchData(){
        DatabaseReference suggRef= FirebaseDatabase.getInstance().getReference("suggestion");
        suggRef.addListenerForSingleValueEvent(listener);
    }

    ValueEventListener listener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            List<String> list=new ArrayList<>();
            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                String sugg=snapshot.getValue(String.class);
                list.add(sugg);
                Log.i("TAG","data : "+sugg );
            }
            adapter=new SuggestionAdapter(SuggestionActivity.this,list);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(SuggestionActivity.this, "Error occured in fetching data", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

