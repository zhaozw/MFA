package com.example.listViewComponents;

import java.util.ArrayList;

import com.example.mfa.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RivalsItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<RivalsItem> itemDetailsArrayList;

	private LayoutInflater l_Inflater;

	public RivalsItemListBaseAdapter(Context context,
			ArrayList<RivalsItem> results) {
		itemDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsArrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.rivals_item, null);
			holder = new ViewHolder();
			holder.txt_itemUserName = (TextView) convertView
					.findViewById(R.id.RivalsItemName);
			holder.txt_itemHitSentDate = (TextView) convertView
					.findViewById(R.id.RivalItemSentHitDate);
			// holder.txt_itemStatsButton = (Button)
			// convertView.findViewById(R.id.RivalsItemStats);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_itemUserName.setText(itemDetailsArrayList.get(position)
				.getUserName());
		holder.txt_itemHitSentDate.setText(itemDetailsArrayList.get(position)
				.getHitSentDate());
		// holder.txt_itemStatsButton.setId(itemDetailsArrayList.get(position).getUserStatsButton().getId());

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemUserName;
		TextView txt_itemHitSentDate;
		// Button txt_itemStatsButton;

	}
}
