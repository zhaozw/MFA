package com.example.activities;

import java.util.Locale;

import com.example.mfa.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Hits extends FragmentActivity implements ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	private ActionBar actionBar;
    
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d("oncreate1", "oncreate stating");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hits_main);
		Log.d("oncreate1", "above actionbar");
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
	}


	@SuppressLint("NewApi")
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
			mViewPager.setCurrentItem(tab.getPosition());			

	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}


	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			switch (position) {
			case 0:
				Fragment hits_0 = new hits_0_menu();
				Bundle hits_0_Bundle = new Bundle();
				hits_0_Bundle.putInt(hits_0_menu.ARG_SECTION_NUMBER, position + 1);
				hits_0.setArguments(hits_0_Bundle);
				return hits_0;
			case 1:
				Fragment hits_1 = new hits_1_menu();
				Bundle hits_1_Bundle = new Bundle();
				hits_1_Bundle.putInt(hits_1_menu.ARG_SECTION_NUMBER, position + 1);
				hits_1.setArguments(hits_1_Bundle);
				return hits_1;
			case 2:
				Fragment hits_2 = new hits_2_menu();
				Bundle hits_2_Bundle = new Bundle();
				hits_2_Bundle.putInt(hits_2_menu.ARG_SECTION_NUMBER, position + 1);
				hits_2.setArguments(hits_2_Bundle);
				return hits_2;
			case 3:
				Fragment hits_3 = new hits_3_menu();
				Bundle hits_3_Bundle = new Bundle();
				hits_3_Bundle.putInt(hits_3_menu.ARG_SECTION_NUMBER, position + 1);
				hits_3.setArguments(hits_3_Bundle);
				return hits_3;
			case 4:
				Fragment hits_4 = new hits_4_menu();
				Bundle hits_4_Bundle = new Bundle();
				hits_4_Bundle.putInt(hits_4_menu.ARG_SECTION_NUMBER, position + 1);
				hits_4.setArguments(hits_4_Bundle);
				return hits_4;
			case 5:
				Fragment hits_5 = new hits_5_menu();
				Bundle hits_5_Bundle = new Bundle();
				hits_5_Bundle.putInt(hits_5_menu.ARG_SECTION_NUMBER, position + 1);
				hits_5.setArguments(hits_5_Bundle);
				return hits_5;
			case 6:
				Fragment hits_6 = new hits_6_menu();
				Bundle hits_6_Bundle = new Bundle();
				hits_6_Bundle.putInt(hits_6_menu.ARG_SECTION_NUMBER, position + 1);
				hits_6.setArguments(hits_6_Bundle);
				return hits_6;
			case 7:
				Fragment hits_7 = new hits_7_menu();
				Bundle hits_7_Bundle = new Bundle();
				hits_7_Bundle.putInt(hits_7_menu.ARG_SECTION_NUMBER, position + 1);
				hits_7.setArguments(hits_7_Bundle);
				return hits_7;
			case 8:
				Fragment hits_8 = new hits_8_menu();
				Bundle hits_8_Bundle = new Bundle();
				hits_8_Bundle.putInt(hits_8_menu.ARG_SECTION_NUMBER, position + 1);
				hits_8.setArguments(hits_8_Bundle);
				return hits_8;
			case 9:
				Fragment hits_9 = new hits_9_menu();
				Bundle hits_9_Bundle = new Bundle();
				hits_9_Bundle.putInt(hits_9_menu.ARG_SECTION_NUMBER, position + 1);
				hits_9.setArguments(hits_9_Bundle);
				return hits_9;
			case 10:
				Fragment hits_10 = new hits_10_menu();
				Bundle hits_10_Bundle = new Bundle();
				hits_10_Bundle.putInt(hits_10_menu.ARG_SECTION_NUMBER, position + 1);
				hits_10.setArguments(hits_10_Bundle);
				return hits_10;
			case 11:
				Fragment hits_11 = new hits_11_menu();
				Bundle hits_11_Bundle = new Bundle();
				hits_11_Bundle.putInt(hits_11_menu.ARG_SECTION_NUMBER, position + 1);
				hits_11.setArguments(hits_11_Bundle);
				return hits_11;
			case 12:
				Fragment hits_12 = new hits_12_menu();
				Bundle hits_12_Bundle = new Bundle();
				hits_12_Bundle.putInt(hits_12_menu.ARG_SECTION_NUMBER, position + 1);
				hits_12.setArguments(hits_12_Bundle);
				return hits_12;
			case 13:
				Fragment hits_13 = new hits_13_menu();
				Bundle hits_13_Bundle = new Bundle();
				hits_13_Bundle.putInt(hits_13_menu.ARG_SECTION_NUMBER, position + 1);
				hits_13.setArguments(hits_13_Bundle);
				return hits_13;
			case 14:
				Fragment hits_14 = new hits_14_menu();
				Bundle hits_14_Bundle = new Bundle();
				hits_14_Bundle.putInt(hits_14_menu.ARG_SECTION_NUMBER, position + 1);
				hits_14.setArguments(hits_14_Bundle);
				return hits_14;
			case 15:
				Fragment hits_15 = new hits_15_menu();
				Bundle hits_15_Bundle = new Bundle();
				hits_15_Bundle.putInt(hits_15_menu.ARG_SECTION_NUMBER, position + 1);
				hits_15.setArguments(hits_15_Bundle);
				return hits_15;
			
			}
			return null;
		}

		@Override
		public int getCount() {
			return 15;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.hits_0_name).toUpperCase(l);
			case 1:
				return getString(R.string.hits_1_name).toUpperCase(l);
			case 2:
				return getString(R.string.hits_2_name).toUpperCase(l);
			case 3:
				return getString(R.string.hits_3_name).toUpperCase(l);
			case 4:
				return getString(R.string.hits_4_name).toUpperCase(l);
			case 5:
				return getString(R.string.hits_5_name).toUpperCase(l);
			case 6:
				return getString(R.string.hits_6_name).toUpperCase(l);
			case 7:
				return getString(R.string.hits_7_name).toUpperCase(l);
			case 8:
				return getString(R.string.hits_8_name).toUpperCase(l);
			case 9:
				return getString(R.string.hits_9_name).toUpperCase(l);
			case 10:
				return getString(R.string.hits_10_name).toUpperCase(l);
			case 11:
				return getString(R.string.hits_11_name).toUpperCase(l);
			case 12:
				return getString(R.string.hits_12_name).toUpperCase(l);
			case 13:
				return getString(R.string.hits_13_name).toUpperCase(l);
			case 14:
				return getString(R.string.hits_14_name).toUpperCase(l);
			case 15:
				return getString(R.string.hits_15_name).toUpperCase(l);
			}
			return null;
		}
	}
	
	
/*
 * 
 * This is the beginning of the fragments
 * 
 * 
 * 
 * */
	
	
	public static class hits_0_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_0_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}
	
	public static class hits_1_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_1_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit1_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}
	
	public static class hits_2_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_2_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit2_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}
	
	
	public static class hits_3_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_3_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_4_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_4_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit4_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_5_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_5_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit5_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_6_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_6_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit6_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_7_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_7_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_8_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_8_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_9_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_9_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_10_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_10_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_11_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_11_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_12_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_12_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_13_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_13_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_14_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_14_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}	public static class hits_15_menu extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
	    
		public hits_15_menu() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_hit0_menu, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.selection_label);
			dummyTextView.setText("");
			return rootView;
		}
		
	}

}
