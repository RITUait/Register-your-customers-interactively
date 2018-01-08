package com.example.ritu.database1app.activity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ritu.database1app.R;
import com.example.ritu.database1app.db.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.ritu.database1app.db.SQLiteHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    private SQLiteHelper db;
    private ListView list ;
    CustomAdapter adapter;
    public MainActivity mainActivity=null;
    public ArrayList<ContactModel> contactModels=new ArrayList<ContactModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivity=this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        db = new SQLiteHelper(this);
        //mainListView = (ListView) findViewById(R.id.mainListView);
        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent =  new Intent(MainActivity.this , GetInfoActivity.class);
               startActivity(intent);
            }
        });
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetInfoActivity.class);
                startActivity(intent);
                Log.e("MainActivity","Qqqqqqq");
            }

        } );
        Log.e("MainA","hello");
        //ArrayList<ContactModel> contactModels = new ArrayList<ContactModel>();
        contactModels = db.getAllRecords();

        // ArrayList<ContactModel> contactModels = db.getAllRecords();
        for (ContactModel n : contactModels ){
            Log.e("MainB","bye" + n );
           // System.out.println(n);
        }

        //listAdapter = new ArrayAdapter<String>(this,R.layout.simplerow, contactModels);
        //mainListView.setAdapter(listAdapter);
        //setListData();
        Resources res= getResources();
        list=(ListView)findViewById(R.id.mainListView);
        adapter = new CustomAdapter(mainActivity,contactModels,res);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void onItemClick(int mPosition){
        ContactModel tempValues=(ContactModel)contactModels.get(mPosition);
        Toast.makeText(mainActivity,""+tempValues.getName()+"email:"+tempValues.getEmail()+"phone:"+tempValues.getPhone(),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
