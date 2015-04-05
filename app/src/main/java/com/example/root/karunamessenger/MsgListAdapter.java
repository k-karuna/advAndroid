package com.example.root.karunamessenger;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 02.04.15.
 */
public class MsgListAdapter extends ArrayAdapter<String> {

    public MsgListAdapter(MessageListActivity messageListActivity, int digitlist_item, String[] digitArray) {
        super(messageListActivity, digitlist_item, digitArray);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String stringData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.digitlist_item, parent, false);
        }
        // Lookup view for data population
        TextView textView = (TextView) convertView.findViewById(R.id.list_text_content);
        LinearLayout item = (LinearLayout) convertView.findViewById(R.id.list_item);
        // Populate the data into the template view using the data object
        textView.setText(stringData);
        if (position % 2 == 1) {
            item.setBackgroundColor(Color.GRAY);
        } else {
            item.setBackgroundColor(Color.WHITE);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
