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

import training.caboose.caboose.Models.Module;
import training.caboose.caboose.Models.ModuleIndex;
import training.caboose.caboose.ViewAdaptors.ModuleViewAdapter;


public class ViewModules extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    SharedPreferences pref;
    String orgId;
    FirebaseUser currentUser;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private SelectionTracker tracker;
    private String positionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_modules);
        mAuth = FirebaseAuth.getInstance();
        pref = getApplicationContext().getSharedPreferences(getString(R.string.userSharedPrefs), 0);
        orgId = pref.getString("orgId", null);
        mRecyclerView = (RecyclerView) findViewById(R.id.viewModulesRCView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Intent intent =  getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null){
            positionId = bd.getString("positionId");
        }


    }

    @Override
    public void onStart() {

        super.onStart();

        currentUser = mAuth.getCurrentUser();
        //send user to login screen if not authenticated

        if (currentUser == null) {
            startActivity(new Intent(ViewModules.this, SplashScreen.class));
        }
        if (orgId == null) {
            startActivity(new Intent(ViewModules.this, SplashScreen.class));
        }
        Log.d("^^^^^^^^^^^^*", currentUser.getUid());
        Log.d("^^^^^^^^^^^*ORG", orgId);
        Log.d("^^^^^^^^^^^*path", "Positions/" + orgId + "/" + positionId + "/modules");

        database.getReference("Positions/" + orgId + "/" + positionId + "/modules")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            List<Module> moduleList = new ArrayList<>();
                            long count;

                            private void createAdapter() {

                                ModuleViewAdapter mAdapter = new ModuleViewAdapter(moduleList, ViewModules.this, positionId);
                                mRecyclerView.setAdapter(mAdapter);
                            }

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                count = dataSnapshot.getChildrenCount();
                                Log.d("^^^^^^^^^^^*ORG", dataSnapshot.toString());

                                for (final DataSnapshot positionSnapshot : dataSnapshot.getChildren()) {
                                    ModuleIndex thisModuleIndex = new ModuleIndex();
                                    final Module thisModule = new Module();
                                    thisModuleIndex.setUid(positionSnapshot.getKey());
                                    thisModuleIndex.setId(positionSnapshot.getValue(ModuleIndex.class).getId());
                                    thisModule.setUid(thisModuleIndex.getId());

                                    database.getReference("Modules/" + orgId + "/" + thisModuleIndex.getId())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    thisModule.setName(dataSnapshot.getValue(Module.class).getName());
                                                    thisModule.setType(dataSnapshot.getValue(Module.class).getType());
                                                    thisModule.setHtmlData(dataSnapshot.getValue(Module.class).getHtmlData());
                                                    thisModule.setYoutubeData(dataSnapshot.getValue(Module.class).getYoutubeData());

                                                    database.getReference("EmployeeResponses/"+orgId+"/" +  currentUser.getUid() +"/"+positionId+"/"+thisModule.getUid()+"/complete").addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            Boolean isComplete = (Boolean)dataSnapshot.getValue();
                                                            if(isComplete){
                                                               thisModule.setComplete(true);

                                                            }
                                                            moduleList.add(thisModule);

                                                            if (--count == 0) {
                                                                createAdapter();
                                                            }


                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
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


