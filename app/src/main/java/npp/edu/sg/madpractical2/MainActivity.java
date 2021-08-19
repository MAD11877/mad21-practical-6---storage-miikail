package npp.edu.sg.madpractical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main Activity";
    private String number;
    private ArrayList<User> userList;

    public String GLOBAL_PREFS = "MyPrefs";
    public String NAME = "NAME";
    public String DESCRIPTION = "DESC";
    public String ID = "ID";
    public String FOLLOWED = "FOLLOWED";

    dbHandler dbHandler = new dbHandler(this);


    SharedPreferences sharedPreferences;
    /*
    public boolean isValidCredentials(String name, String desc, int id, boolean followed)
    {
        sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedName = sharedPreferences.getString(NAME, "");
        String sharedDescription = sharedPreferences.getString(DESCRIPTION, "");
        String sharedId = sharedPreferences.getString(ID, "");
        String sharedFollowed = sharedPreferences.getString(FOLLOWED, "");

        if(sharedName.equals(name) && sharedDescription.equals(desc) && sharedId.equals(id) && sharedFollowed.equals(followed))
        {
            return true;
        }
        return false;

        User dbData = dbHandler.findUser(name, desc, id, followed);
        if(dbData == null)
        {
            Toast.makeText(this,"User does not exist", Toast.LENGTH_SHORT).show();

        }
        else
        {
            if (dbData.getName().equals(name) && dbData.getDescription().equals(desc) && dbData.getId().equals(id) && ;
        }
        
    }*/

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Main Activity Created");

        TextView nameTxt = findViewById(R.id.nameTextView);
        TextView descTxt = findViewById(R.id.descTextView);

        String name = "Name not set";
        String desc = "Description not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name = extras.getString("name");
            desc = extras.getString("desc");
        }

        nameTxt.setText(name);
        descTxt.setText("Description " + desc);

        Button followButton = findViewById(R.id.followButton);
        Button msgButton = findViewById(R.id.msgButton);

        int id = getIntent().getIntExtra("id", 0);
        Boolean followed = getIntent().getBooleanExtra("followed", false);
        User user = new User();


        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Button Pressed!");

                if(user.isFollowed() == false) {
                    followButton.setText("Unfollow");
                    user.followed = true;
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else if (user.isFollowed() == true) {
                    followButton.setText("Follow");
                    user.followed = false;
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setFollowBtn() {
        Button b = findViewById(R.id.followButton);
        if(user.followed) {
            b.setText("Unfollow");
        }
        else {
            b.setText("Follow");
        }
    }
    public void onFollowClick(View v) {
        user.followed = !user.followed;
        if(user.followed)
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();
        setFollowBtn();

        dbHandler db = new dbHandler(this);
        db.updateUser(user);
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