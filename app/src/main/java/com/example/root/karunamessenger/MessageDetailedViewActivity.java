package com.example.root.karunamessenger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;



public class MessageDetailedViewActivity extends FragmentActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private long elementId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view_viewpager);
        Intent intent = getIntent();
        this.elementId = intent.getLongExtra("ELEMENT_ID", 0);
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem((int) elementId);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MessageViewFragment messageViewFragment = new MessageViewFragment();
            Bundle args = new Bundle();
            args.putInt("ELEMENT_ID", position);
            messageViewFragment.setArguments(args);
            return messageViewFragment;
        }

        @Override
        public int getCount() {
            MyDB dbHelper = new MyDB(getApplicationContext());
            return dbHelper.selectRecords().getCount();
        }
    }
}
