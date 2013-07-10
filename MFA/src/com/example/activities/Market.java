package com.example.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.listViewComponents.ItemDetails;
import com.example.listViewComponents.ItemListBaseAdapter;
import com.example.mfa.R;

public class Market extends Activity {

	Resources res;
	ArrayList<ItemDetails> image_details;
	SharedPreferences settings;
	AlertDialog.Builder marketDialog;
	SharedPreferences.Editor editor;
	TextView cash;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // no title
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_market);

		settings = getSharedPreferences("UnlocksFile", 0);

		res = this.getResources();
		cash = (TextView) findViewById(R.id.Cash_Market);
		cash.setText(settings.getInt("Cash", 0) + "");
		editor = settings.edit();

		image_details = GetSearchResults();

		final ListView lv1 = (ListView) findViewById(R.id.MarketListView);
		lv1.setAdapter(new ItemListBaseAdapter(this, image_details));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				if (position != 0) {
					Object o = lv1.getItemAtPosition(position);
					ItemDetails selectedItem = (ItemDetails) o;
					if (settings.getBoolean(
							"Market_Item_" + selectedItem.getListPosition()
									+ "_Purchased", false))
						itemAlreadyPurchasedToast();
					else
						createPurchaseDialog(selectedItem);
				} else {
					attemptPurchaseOfInGameCurrency();
				}
			}
		});

		Intent intent = getIntent();
		if (intent.getIntExtra("DesiredPurchase", -1) == 0) {
			attemptPurchaseOfInGameCurrency();
		} else if (intent.getIntExtra("DesiredPurchase", -1) > 0) {
			Object o = lv1.getItemAtPosition(intent.getIntExtra(
					"DesiredPurchase", -1));
			ItemDetails selectedItem = (ItemDetails) o;
			createPurchaseDialog(selectedItem);
		}

	}

	private void createPurchaseDialog(final ItemDetails item) {

		DialogInterface.OnClickListener marketDialogListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					attemptPurchase(item);
					break;
				case DialogInterface.BUTTON_NEGATIVE:

					break;
				}
			}
		};

		marketDialog = new AlertDialog.Builder(this);
		marketDialog.setMessage(getString(R.string.Purchase) + " "
				+ item.getName() + " " + getString(R.string.For) + " "
				+ item.getPrice() + " " + getString(R.string.Points));
		marketDialog.setPositiveButton(getString(R.string.Yes),
				marketDialogListener);
		marketDialog.setNegativeButton(getString(R.string.No),
				marketDialogListener);
		marketDialog.show();

	}

	protected void attemptPurchaseOfInGameCurrency() {

		DialogInterface.OnClickListener purchaseInGameCurrency = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:

					int currentCash = settings.getInt("Cash", 0);
					editor.putInt("Cash", currentCash + 1000);
					editor.commit();
					cash.setText(settings.getInt("Cash", 0) + "");

					break;
				case DialogInterface.BUTTON_NEGATIVE:
					Toast toast2 = Toast.makeText(getApplicationContext(),
							"Ok, I guess us devlopers can live off toast   ):",
							Toast.LENGTH_SHORT);
					toast2.show();
					break;
				}
			}
		};

		marketDialog = new AlertDialog.Builder(this);
		marketDialog.setTitle(getString(R.string.DenyPurchase));
		marketDialog.setMessage(getString(R.string.PurchaseCashMesage));
		marketDialog.setPositiveButton(getString(R.string.Yes),
				purchaseInGameCurrency);
		marketDialog.setNegativeButton(getString(R.string.No),
				purchaseInGameCurrency);
		marketDialog.show();

	}

	private void attemptPurchase(ItemDetails item) {

		if (settings.getInt("Cash", 0) >= Integer.parseInt(item.getPrice())) {
			editor.putBoolean("Market_Item_" + item.getListPosition()
					+ "_Purchased", true);
			editor.commit();
			int currentCash = settings.getInt("Cash", 0);
			editor.putInt("Cash",
					currentCash - Integer.parseInt(item.getPrice()));
			editor.commit();
			cash.setText(settings.getInt("Cash", 0) + "");
			confirmPurchaseToast();
		} else {
			attemptPurchaseOfInGameCurrency();
		}

	}

	private void confirmPurchaseToast() {
		Toast toast = Toast.makeText(getApplicationContext(),
				getString(R.string.ConfirmPurchase), Toast.LENGTH_LONG);
		toast.show();
	}

	private void denyPurchaseToast() {
		Toast toast = Toast.makeText(getApplicationContext(),
				getString(R.string.DenyPurchase), Toast.LENGTH_LONG);
		toast.show();
	}

	private void itemAlreadyPurchasedToast() {
		Toast toast = Toast.makeText(getApplicationContext(),
				getString(R.string.ItemAlreadyPurchased), Toast.LENGTH_LONG);
		toast.show();
	}

	private ArrayList<ItemDetails> GetSearchResults() {
		ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

		ItemDetails item_details;
		for (int k = 0; k <= 30; k++) {

			item_details = new ItemDetails();

			item_details.setName(getString(getResources().getIdentifier(
					"Market_Item_" + k + "_Name", "string", getPackageName())));

			item_details.setItemDescription(getString(getResources()
					.getIdentifier("Market_Item_" + k + "_Description",
							"string", getPackageName())));

			item_details
					.setImageID(getResources().getIdentifier(
							"market_item_" + k + "_icon", "drawable",
							getPackageName()));

			item_details.setPurchased(settings.getBoolean("Market_Item_" + k
					+ "_Purchased", false));

			if (item_details.getPurchased())
				item_details.setPrice(getString(R.string.Purchased));
			else
				item_details.setPrice(getString(getResources().getIdentifier(
						"Market_Item_" + k + "_Price", "string",
						getPackageName())));

			item_details.setListPosition(k);

			results.add(item_details);

		}

		return results;
	}

}