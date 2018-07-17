package training.caboose.caboose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import training.caboose.caboose.Models.Position;
import training.caboose.caboose.Models.PositionIndex;
import training.caboose.caboose.ViewAdaptors.PositionViewAdapter;


public class ViewPositions extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences pref;
    String orgId;
    FirebaseUser currentUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private SelectionTracker tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_positions);
        mAuth = FirebaseAuth.getInstance();
        pref = getApplicationContext().getSharedPreferences(getString(R.string.userSharedPrefs), 0);
        orgId = pref.getString("orgId", null);
        mRecyclerView = (RecyclerView) findViewById(R.id.positionsView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


    }

    @Override
    public void onStart() {

        super.onStart();

        currentUser = mAuth.getCurrentUser();
        //send user to login screen if not authenticated

        if (currentUser == null) {
            startActivity(new Intent(ViewPositions.this, SplashScreen.class));
        }
        if (orgId == null) {
            startActivity(new Intent(ViewPositions.this, SplashScreen.class));
        }
        Log.d("*******************", currentUser.getUid());
        Log.d("*******************ORG", orgId);

        database.getReference("Employees/" + orgId + "/" + currentUser.getUid() + "/positions")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            List<Position> positionList = new ArrayList<>();
                            long count;

                            private void createAdapter() {
                                PositionViewAdapter mAdapter = new PositionViewAdapter(positionList, ViewPositions.this);
                                mRecyclerView.setAdapter(mAdapter);
                            }

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                count = dataSnapshot.getChildrenCount();

                                for (final DataSnapshot positionSnapshot : dataSnapshot.getChildren()) {
                                    PositionIndex thisPosIndex = new PositionIndex();
                                    final Position thisPos = new Position();
                                    thisPosIndex.setUid(positionSnapshot.getKey());
                                    thisPosIndex.setId(positionSnapshot.getValue(PositionIndex.class).getId());
                                    thisPosIndex.setAssignedDate(positionSnapshot.getValue(PositionIndex.class).getAssignedDate());
                                    thisPos.setUid(thisPosIndex.getId());
                                    thisPos.setAssignedData(thisPosIndex.getAssignedDate());
                                    database.getReference("Positions/" + orgId + "/" + thisPosIndex.getId())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    thisPos.setName(dataSnapshot.getValue(Position.class).getName());
                                                    positionList.add(thisPos);
                                                    if (--count == 0) {
                                                        createAdapter();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //handle error
                            }
                        }
                );
    }
}
