package com.example.root.karunamessenger;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MessageViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_message_view, container, false);
        MyDB dbHelper = new MyDB(KarunaMessenger.getAppContext());

        int elementId = getArguments().getInt("ELEMENT_ID");
        TextView textViewTitle =  (TextView) rootView.findViewById(R.id.fragment_title_text);
        TextView textViewDescription =  (TextView) rootView.findViewById(R.id.fragment_description_text);
        TextView textViewNumPage =  (TextView) rootView.findViewById(R.id.fragment_num_page);
        Cursor cursor =  dbHelper.selectRecords();
        cursor.moveToPosition(elementId);
        textViewNumPage.setText(elementId + "");
        textViewTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
        if (cursor.getString(cursor.getColumnIndex("info")) == null) {
            textViewDescription.setText("Нет описания");
        } else {
            textViewDescription.setText(cursor.getString(cursor.getColumnIndex("info")));
        }
        return rootView;
    }
}
