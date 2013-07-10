package com.example.objects;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mfa.R;
import com.example.mfa.networking.PlayerStatsObject;

public class RivalAdapter extends ArrayAdapter<PlayerStatsObject> {

	Context context;
	int layoutResourceId;
	PlayerStatsObject data[] = null;

	public RivalAdapter(Context context, int layoutResourceId,
			PlayerStatsObject[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RivalHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RivalHolder();
			holder.Name = (TextView) row.findViewById(R.id.Name);

			row.setTag(holder);
		} else {
			holder = (RivalHolder) row.getTag();
		}

		PlayerStatsObject rival = data[position];
		holder.Name.setText(rival.name);
		return row;
	}

	static class RivalHolder {
		TextView Name;
	}
}
