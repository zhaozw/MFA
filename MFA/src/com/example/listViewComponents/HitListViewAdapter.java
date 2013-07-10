package com.example.listViewComponents;

import java.util.ArrayList;

import com.example.mfa.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HitListViewAdapter extends BaseAdapter {
	private static ArrayList<HitListViewItem> itemDetailsrrayList;

	private Integer[] imgid;

	private LayoutInflater l_Inflater;

	public HitListViewAdapter(Context context, ArrayList<HitListViewItem> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
		imgid = new Integer[results.size()];
		for (int k = 0; k < results.size(); k++) {
			Log.d("Damnit", results.get(k).getImageID() + "results");
			imgid[k] = results.get(k).getImageID();
		}

	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.hit_details_view, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView
					.findViewById(R.id.name);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getHitName());

		holder.itemImage.setImageResource(imgid[position]);


		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		ImageView itemImage;
	}
}
