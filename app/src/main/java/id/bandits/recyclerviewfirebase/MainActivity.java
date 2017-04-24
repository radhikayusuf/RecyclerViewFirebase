package id.bandits.recyclerviewfirebase;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.bandits.recyclerviewfirebase.adapter.MainAdapter;
import id.bandits.recyclerviewfirebase.dao.MainDao;

public class MainActivity extends AppCompatActivity {

    private List<MainDao> mData = new ArrayList<>();
    private RecyclerView myrecyclerview;
    private FloatingActionButton faButton;
    private LinearLayoutManager layoutManagerList;
    private GridLayoutManager layoutManagerGrid;
    private MainAdapter adapter;

    private FirebaseDatabase myfdatabase;
    private DatabaseReference databaseReference;

    private static int recycler_type = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myfdatabase = FirebaseDatabase.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = myfdatabase.getReference("data");
        initMyComponent(recycler_type);
    }

    private void initMyComponent(int recycler_type) {
        myrecyclerview = (RecyclerView) findViewById(R.id.myrecyclerview);
        faButton = (FloatingActionButton) findViewById(R.id.myFAB);
        layoutManagerList = new LinearLayoutManager(this);
        layoutManagerGrid = new GridLayoutManager(this, 2);

        adapter = new MainAdapter(mData, recycler_type);
        myrecyclerview.setAdapter(adapter);
        myrecyclerview.setLayoutManager(recycler_type == MainAdapter.TYPE_LIST ? layoutManagerList : layoutManagerGrid);
        retrievingData();
    }

    private void retrievingData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mData.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Long id = (Long)snapshot.child("id").getValue();
                    String title = (String)snapshot.child("title").getValue();
                    String image = (String)snapshot.child("image").getValue();

                    mData.add(new MainDao(String.valueOf(id), image, title));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeRC(View view) {
        if(recycler_type == MainAdapter.TYPE_LIST){
            recycler_type = MainAdapter.TYPE_GRID;
            initMyComponent(recycler_type);
            faButton.setImageResource(R.drawable.ic_format_list_bulleted_white_24dp);
        }else{
            recycler_type = MainAdapter.TYPE_LIST;
            initMyComponent(recycler_type);
            faButton.setImageResource(R.drawable.ic_grid_on_white_24dp);
        }
    }
}
