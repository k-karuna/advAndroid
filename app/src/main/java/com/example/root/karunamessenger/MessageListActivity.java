package com.example.root.karunamessenger;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MessageListActivity extends ActionBarActivity {

    public static String[] digitArray = new String[1000];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ListView messageListView = (ListView) findViewById(R.id.messageListView);
        final String[] tens = new String[] { "одиннадцать", "двенадцать", "тринадцать",
        "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
        final String[] ones = new String[] { "один", "два", "три",
                "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        final String[] tenszr = new String[] { "десять", "двадцать", "тридцать",
                "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        final String[] hundreds = new String[] { "сто", "двести", "триста",
                "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        int hundr;
        int ten;
        for (int dg = 1; dg < 1001; dg++) {
            hundr = dg / 100;
            ten = dg / 10;
            if (dg > 0 && dg < 10 ) {
                digitArray[dg - 1] = ones[dg - 1];
            }
            if (dg == 10) {
                digitArray[dg - 1] = tenszr[0];
            }
            if (dg > 10 && dg < 20) {
                digitArray[dg - 1] = tens[dg % 10 -1];
            }
            if (dg > 19 && dg < 100) {
                if (dg % 10 == 0) {
                    digitArray[dg - 1] = tenszr[ten - 1];
                } else {
                    digitArray[dg - 1] = tenszr[ten - 1] + " " + ones[dg % 10 - 1];
                }
            }

            if (dg > 99 && dg < 1000) {
                if (dg % 100 > 10 && dg % 100 < 20) {
                    digitArray[dg - 1] = hundreds[hundr - 1] + tens[dg % 10 - 1];
                } else {
                    if (dg % 10 == 0 && dg / 10 % 10 == 0) {
                        digitArray[dg - 1] = hundreds[hundr - 1];
                    }
                    if (dg % 10 == 0 && dg / 10 % 10 != 0) {
                        digitArray[dg - 1] = hundreds[hundr - 1] + " " + tenszr[dg / 10 % 10 - 1];
                    }
                    if (dg % 10 != 0 && dg / 10 % 10 == 0) {
                        digitArray[dg - 1] = hundreds[hundr - 1] + " " + ones[dg % 10 - 1];
                    }
                    if (dg % 10 != 0 && dg / 10 % 10 != 0) {
                        digitArray[dg - 1] = hundreds[hundr - 1] + " " + tenszr[dg % 100 / 10 - 1] + " " + ones[dg % 10 - 1];
                    }
                }
            } else if (dg == 1000) {
                digitArray[dg - 1] = "одна тысяча";
            }
        }
        MyDB dbHelper = new MyDB(getApplicationContext());
        Cursor cursor = dbHelper.selectRecords();
        MessageListCursorAdapter messageListCursorAdapter = new MessageListCursorAdapter(this, cursor);
        messageListView.setAdapter(messageListCursorAdapter);
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(MessageListActivity.this, MessageDetailedViewActivity.class);
                intent.putExtra("ELEMENT_ID", arg3);
                MessageListActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_list, menu);
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
