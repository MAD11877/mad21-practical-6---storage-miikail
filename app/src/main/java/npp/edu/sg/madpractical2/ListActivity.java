package npp.edu.sg.madpractical2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "Activity List";
    private ArrayList<User> userList;
    private RecyclerView recyclerView;

    private recyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG, "Activity List Created");

        dbHandler db = new dbHandler(this);

        recyclerView = findViewById(R.id.recyclerView);
        userList = db.getUsers();

        setUserInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListener();

        recyclerAdapter adapter = new recyclerAdapter(userList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new recyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", userList.get(position).getName());
                intent.putExtra("desc", userList.get(position).getDescription());
                Alert(position);
            }
        };
    }

    public void Alert(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage(userList.get(position).getName());
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name", userList.get(position).getName());
                intent.putExtra("desc", userList.get(position).getDescription());
                intent.putExtra("id", userList.get(position).getId());
                intent.putExtra("followed", userList.get(position).isFollowed());
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }


    private void setUserInfo() {
        // 20 users with random name
        for (int i = 0; i < 20; i++) {
            userList.add(RandomUser(RandomNum(), String.valueOf(RandomNum())));
        }
    }

    public int RandomNum() {
        Random random = new Random();
        int x = random.nextInt(999999999);
        return x;
    }

    public User RandomUser(int number, String desc) {

        User user = new User();
        return user;
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.v(TAG, "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v(TAG, "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.v(TAG, "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.v(TAG, "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v(TAG, "Destroy");
    }
}