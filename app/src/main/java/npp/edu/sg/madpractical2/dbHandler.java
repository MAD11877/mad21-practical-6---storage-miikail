package npp.edu.sg.madpractical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHandler extends SQLiteOpenHelper {

/*    public static String DATABASE_NAME = "accountsDN.db";
    public static String ACCOUNTS = "Accounts";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOWED = "Followed";
    public static int DATABASE_VERSION = 1;*/

    private static final int VERSION = 1;
    private static final String DATABASE = "users.db";

    public dbHandler(@Nullable Context context)
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE user (name TEXT, description TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT, followed INTEGER)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public ArrayList<User> getUsers()
    {
        ArrayList<User> list = new ArrayList<User> ();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        while(cursor.moveToNext())
        {
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = cursor.getInt(2);
            u.followed = cursor.getInt(3)==0?false:true;

            list.add(u);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followed", user.followed);
        int count = db.update("user", values, "id = ?", new String[]{"" + user.id});

        db.close();
    }
}
