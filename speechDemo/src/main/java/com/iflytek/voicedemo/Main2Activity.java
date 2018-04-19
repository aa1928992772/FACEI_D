/*
package com.iflytek.voicedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends Activity {
    private MyDatabaseHelper dbHelper;
    Boolean add = false;
    String allid = null;
//    private String result = "";

//    TextView noise_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main2);


        dbHelper = new MyDatabaseHelper(this,"Note.db",null,1);

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.text);
        Button save_button = (Button) findViewById(R.id.save);
        Button delete_button = (Button) findViewById(R.id.delete);
        TextView create_time = (TextView)findViewById(R.id.create_time);
//        noise_time = (TextView)findViewById(R.id.noise_time);

        //弹窗
//        Button diglog_button = (Button)findViewById(R.id.dialog);
//
//
//        diglog_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // time
//
//                Calendar cale2 = Calendar.getInstance();
//                TimePickerDialog d = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        result += hourOfDay + "时" + minute + "分";
//                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                    }
//
//                }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true);
//
//                d.show();
//                d.setButton(DialogInterface.BUTTON_NEGATIVE,"取消",new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (which == DialogInterface.BUTTON_NEGATIVE) {
//                            Toast.makeText(getApplicationContext(), "cancel1", Toast.LENGTH_SHORT).show();
////                            finish();
//                            onCreate(null);
////                            Intent it = new Intent(Main2Activity.this, Main2Activity.class);
////                            startActivity(it);
////                            noise_time.setText("hahahah");
//
//
//                            //取消操作
//                        }
//                    }
//
//                });


                // date
//                Calendar cale1 = Calendar.getInstance();
//                new DatePickerDialog(Main2Activity.this,new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                          int dayOfMonth) {
//                        //这里获取到的月份需要加上1哦~
//                        result += year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
//                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//                    }
//                }
//                        ,cale1.get(Calendar.YEAR)
//                        ,cale1.get(Calendar.MONTH)
//                        ,cale1.get(Calendar.DAY_OF_MONTH)).show();
//
//
//
//            }
//        });


                Intent intent = getIntent();
                String contentid = intent.getStringExtra("contentid");
                allid = contentid;
                if (allid == null) {
                    System.out.print("add click here!");

                } else {
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    String content = null;
                    String time = null;
                    Cursor c = db.query("book", null, "id=?", new String[]{contentid}, null, null, null);
                    if (c.moveToFirst()) {
                        do {
                            content = c.getString(c.getColumnIndex("content"));
                            time = c.getString(c.getColumnIndex("time"));

                        } while (c.moveToNext());
                    }
                    c.close();
                    create_time.setText(time);
                    textView.setText(content);
                }


                save_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (allid == null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                            Date curDate = new Date(System.currentTimeMillis());
                            String str = formatter.format(curDate);
                            String content = textView.getText().toString();
                            String id = null;

                            if ("".equals(content)) {

                                System.out.print("blank content!");
                                Intent it = new Intent(Main2Activity.this, MainActivity2.class);
                                startActivity(it);
                                finish();
                            } else {
                                if (allid == null && !"".equals(content)) {

                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    ContentValues values = new ContentValues();
                                    values.put("content", content);
                                    values.put("time", str);
                                    if (add == false) {
                                        db.insert("book", null, values);
                                        values.clear();
                                        Cursor c = db.query("book", null, "content=?", new String[]{content}, null, null, null);
                                        if (c.moveToFirst()) {
                                            do {
                                                allid = c.getString(c.getColumnIndex("id"));

                                            } while (c.moveToNext());
                                        }
                                        c.close();

                                        Toast.makeText(Main2Activity.this, "add successed !",
                                                Toast.LENGTH_SHORT).show();

                                    }
//                            else if (add==true){
//                                db.update("book",values,"id=?",new String[]{allid});
//                                Toast.makeText(Main2Activity.this, "add successed !",
//                                        Toast.LENGTH_SHORT).show();
//                            }
                                    add = true;


                                } else {
                                    System.out.print("nnnn");


                                }

                            }

                        } else {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                            Date curDate = new Date(System.currentTimeMillis());
                            String str = formatter.format(curDate);
                            String content = textView.getText().toString();
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("content", content);
                            values.put("time", str);
                            db.update("book", values, "id=?", new String[]{allid});
                            Toast.makeText(Main2Activity.this, "save successed !",
                                    Toast.LENGTH_SHORT).show();


                        }


                    }
                });

                delete_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        db.delete("book", "id=?", new String[]{allid});
                        Toast.makeText(Main2Activity.this, "delete !",
                                Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(Main2Activity.this, MainActivity2.class);
                        startActivity(it);
                        finish();

                    }
                });
            }
//        });}



//    @Override
//    protected void onResume(){
//        super.onResume();
//        Toast.makeText(Main2Activity.this, "onResume !",
//                Toast.LENGTH_LONG).show();
//
//
//    }



    @Override
    public void onBackPressed(){
        Intent it = new Intent(Main2Activity.this, MainActivity2.class);
        startActivity(it);
        finish();
        super.onBackPressed();

    }
}

*/
