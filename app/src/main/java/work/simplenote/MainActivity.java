package work.simplenote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Note> notesList = new ArrayList<Note>();
    private MyDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"Note.db",null,1);
        query();
        NoteAdapter adapter = new NoteAdapter(MainActivity.this,R.layout.item,notesList);
        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button jump_button = (Button) findViewById(R.id.add);
        jump_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();

            }
        });
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        View view1 = listView.getChildAt(position);
                        TextView text = (TextView)view1.findViewById(R.id.id);
                        String contentid = text.getText().toString();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("contentid", contentid);
                        startActivity(intent);
                        finish();
                    }
                }
        );


    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();

    }




    private void query(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String id = cursor.getString(cursor.getColumnIndex("id"));

                Note item = new Note(content, time, id);
                notesList.add(item);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }



}


