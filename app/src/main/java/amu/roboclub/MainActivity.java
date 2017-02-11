package amu.roboclub;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import amu.roboclub.Object.Key;
import amu.roboclub.Object.Keys;
import amu.roboclub.Object.User;
import amu.roboclub.Object.Users;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private Users users = new Users();
    private Key keys[] = new Key[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getKeyDatabase();
        getUserDatabase();
    }
    public void getKeyDatabase(){
        FirebaseDatabase keyDatabase = FirebaseDatabase.getInstance();
        DatabaseReference keyReference = keyDatabase.getReference("/keys");
        keyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Keys key = dataSnapshot.getValue(Keys.class);
                keys[0] = key.getKey1();
                keys[1] = key.getKey2();
                keys[2] = key.getKey3();
                keys[3] = key.getKey4();
                Log.d (TAG, key.getKey1().getCurrent());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getUserDatabase(){
        FirebaseDatabase userDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userReference = userDatabase.getReference("/users");
        userReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG,user.getName());
                users.addEntry(user.getUid(),user);
                try{
                    User u = users.getUser(keys[0].getCurrent());
                    Log.d(TAG + "checking name", u.getName());
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;
    }
}
