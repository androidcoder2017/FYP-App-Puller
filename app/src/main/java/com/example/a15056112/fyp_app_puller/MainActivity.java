package com.example.a15056112.fyp_app_puller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseGates;
    private DatabaseReference mDatabaseCurrentUser;

    private Query mQuery;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    ArrayList<User> users = new ArrayList<User>();

    ListView lv;

    List<Gate> gateList;

    GateAdapter adapter;

    String name, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.RED));

        lv = (ListView)findViewById(R.id.lv);

        mDatabaseGates = FirebaseDatabase.getInstance().getReference().child("Gate");
        mQuery = mDatabaseGates.orderByChild("gateName");
        gateList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, activity_login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                } else {
                    String currentUserId = mAuth.getCurrentUser().getUid();
                    mDatabaseCurrentUser = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
                    retrieve();

                }
            }
        };


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gate gate = gateList.get(position);
                Intent intent = new Intent(getApplicationContext(), GateInformation.class);
                intent.putExtra("terminalname", gate.getTerminalName());
                intent.putExtra("gatename", gate.getGateName());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                gateList.clear();

                for (DataSnapshot gateSnapshot: dataSnapshot.getChildren()) {
                    Gate gate = gateSnapshot.getValue(Gate.class);
                    gateList.add(gate);
                }

                final GateAdapter adapter = new GateAdapter(MainActivity.this, gateList);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Gate> gateSearchList = new ArrayList<Gate>();
                ListView lvSearch;

                lvSearch = (ListView)findViewById(R.id.lv);
                for (Gate gate : gateList) {
                    if (gate.getGateName().toLowerCase().contains(newText.toLowerCase())) {
                        gateSearchList.add(gate);
                        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Gate gate = gateSearchList.get(position);
                                Intent intent = new Intent(getApplicationContext(), GateInformation.class);
                                intent.putExtra("terminalname", gate.getTerminalName());
                                intent.putExtra("gatename", gate.getGateName());
                                startActivity(intent);
                            }
                        });
                    }
                }

                adapter = new GateAdapter(MainActivity.this, gateSearchList);
                lvSearch.setAdapter(adapter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        } else if(item.getItemId() == R.id.action_message) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            intent.putExtra("name", name + "("+ role +")");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
    }

    public ArrayList<User> retrieve() {
        mDatabaseCurrentUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                if(user.getRole().equals("Puller")){
                    Toast.makeText(MainActivity.this, "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();
                    name = user.getName().toString();
                    role = user.getRole().toString();
                    Log.i("TAG", "onDataChange: "+ name);
                } else {
                    Toast.makeText(MainActivity.this, "Error your account is not eligible to login", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, activity_login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return users;
    }

}
