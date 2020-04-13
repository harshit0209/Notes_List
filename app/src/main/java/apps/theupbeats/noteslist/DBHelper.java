package apps.theupbeats.noteslist;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class DBHelper extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME = "List.db";
    public static final  String TABLE_NAME = "Student_table";
    public static final  String COL_1 = "ID";
    public static final  String COL_2 = "Title";
    public static final  String COL_3 = "Content";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Content TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public boolean insertData(String title,String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,content);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        //To Check Whether Data is Inserted in DataBase
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData()

    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+ TABLE_NAME,null);
        return res;
    }
    public Map fetchData()
    {
        //for three values using Entry
        Map<Integer ,String> actionMap = new HashMap<Integer,String>();
        int i=0;
        Cursor res=getAllData();
        StringBuffer stringBuffer=new StringBuffer();
        if(res!=null && res.getCount()>0)
        {
            while(res.moveToNext())
            {
                actionMap.put(i++,(res.getString(1)+""+(char)99999+ res.getString(2)));


                }
        }
        //else first launch not to load anything to list
        return  actionMap;

    }
}