package com.example.listViewComponents;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mfa.R;

public class ImageListViewAdapter extends BaseAdapter {

	private ArrayList<Integer> imageIds;

	private LayoutInflater l_Inflater;

	private int selectedPosition = -1;

	public ImageListViewAdapter(Context context, ArrayList<Integer> results) {
		imageIds = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return imageIds.size();
	}

	public Integer getItem(int position) {
		return imageIds.get(position);
	}

	public int getImageId(int position) {
		return imageIds.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.single_image_list_view,
					null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.itemImage.setImageResource(imageIds.get(position));

		return convertView;
	}

	public int getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	static class ViewHolder {
		ImageView itemImage;
	}

}
