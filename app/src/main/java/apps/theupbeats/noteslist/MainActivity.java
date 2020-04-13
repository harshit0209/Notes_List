package apps.theupbeats.noteslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends ListActivity {

    FloatingActionButton floatingActionButton;
     ArrayAdapter<String> adapter;
    ArrayList arrayList;
    Intent intent;
    Map<String, String> actionMap;
    DBHelper dbHelper;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    public void initUI()
    {

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Lets Make a Note !");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        dbHelper=new DBHelper(this);
        floatingActionButton=findViewById(R.id.floatingActionButton5);
         arrayList = new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        setListAdapter(adapter);
        intent=new Intent(this,MakeNoteActivity.class);
        showActionMap();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("show",false);
                startActivityForResult(intent,2);


            }
        });
    }


    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);


        actionMap=dbHelper.fetchData();
        intent.putExtra("show",true);
        intent.putExtra("data",actionMap.get(position));
        startActivity(intent);
     //   String selectedItem = (String) getListView().getItemAtPosition(position);
        //String selectedItem = (String) getListAdapter().getItem(position);

       // Toast.makeText(this, "You clicked " + actionMap.get(position).split(""+(char)99999)[1] + " at position " + position, Toast.LENGTH_SHORT).show();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String ress=data.getStringExtra("result");
            arrayList.add(ress);

            adapter.notifyDataSetChanged();

        }
    }





    void showActionMap()
    {
        actionMap=dbHelper.fetchData();
        Toast.makeText(this, ""+actionMap.size(), Toast.LENGTH_SHORT).show();
        int l=actionMap.size();
        String temp;
        for(int i=0;i<l;i++)
        {
            temp=actionMap.get(i);
            temp=temp.split(""+(char)99999)[0];
            arrayList.add(temp);
            System.out.println(temp);
            adapter.notifyDataSetChanged();

        }

    }

}
