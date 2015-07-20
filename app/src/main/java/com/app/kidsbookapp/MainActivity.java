package com.app.kidsbookapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.kidsbookapp.R;
import com.google.android.gms.ads.AdSize;

public class MainActivity extends Activity implements OnClickListener{

	private ImageView img_alphabet , img_numbers , img_colors , img_shapes , img_vegetables , img_fruits,
	img_clothes , img_food , img_pob , img_vehicle , img_star;
	private Intent intent;
	private TextView headingMain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		onClickView();
		showAdd();
	}
	
	 private void showAdd() {
		com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
		adView.setAdUnitId("ca-app-pub-1878227272753934/3575367600");
		adView.setAdSize(AdSize.BANNER);
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.rel_bottom_main);        
		layout.addView(adView);
		com.google.android.gms.ads.AdRequest request = new com.google.android.gms.ads.AdRequest.Builder().build();
		adView.loadAd(request);
	}
	
		

	private void onClickView() {
		img_alphabet.setOnClickListener(this);
		img_numbers.setOnClickListener(this);
		img_colors.setOnClickListener(this); 
		img_shapes.setOnClickListener(this);
		img_vegetables.setOnClickListener(this); 
		img_fruits.setOnClickListener(this); 
		img_clothes.setOnClickListener(this);
		img_food.setOnClickListener(this);
		img_pob.setOnClickListener(this);
		img_vehicle.setOnClickListener(this);
		img_star.setOnClickListener(this);
	}

	private void initView() {
		
		img_alphabet = (ImageView) findViewById(R.id.img_alphabet);
		img_numbers = (ImageView) findViewById(R.id.img_numbers);
		img_colors = (ImageView) findViewById(R.id.img_colors);
		img_shapes = (ImageView) findViewById(R.id.img_shapes);
		img_vegetables = (ImageView) findViewById(R.id.img_vegetables);
		img_fruits = (ImageView) findViewById(R.id.img_fruits);
		img_clothes = (ImageView) findViewById(R.id.img_clothes);
		img_food = (ImageView) findViewById(R.id.img_food);
		img_pob = (ImageView) findViewById(R.id.img_pob);
		img_vehicle = (ImageView) findViewById(R.id.img_vehicle);
		headingMain = (TextView) findViewById(R.id.headingMain);
		img_star = (ImageView) findViewById(R.id.img_star);

		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/" + "Hobo.ttf");
		headingMain.setTypeface(tf);
		
	}
	
	private void app(){
		Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
		  startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
		  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_alphabet:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "alphabet");
			startActivity(intent);
			break;
		case R.id.img_numbers:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "number");
			startActivity(intent);
			break;
		case R.id.img_colors:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "color");
			startActivity(intent);
			break;
		case R.id.img_shapes:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "shapes");
			startActivity(intent);
			break;
		case R.id.img_vegetables:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "vegetables");
			startActivity(intent);
			break;
		case R.id.img_fruits:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "fruits");
			startActivity(intent);
			break;
		case R.id.img_star:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Kids Book");
			alertDialog.setMessage("Rate This Application");
			alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int which) {
	            	app();
	            }
	        });
	 
	        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {	            
	            dialog.cancel();
	            }
	        });
			alertDialog.show();			
			
			break;
		case R.id.img_clothes:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "clothes");
			startActivity(intent);
			break;
		case R.id.img_food:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "food");
			startActivity(intent);
			break;
		case R.id.img_pob:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "pob");
			startActivity(intent);
			break;
		case R.id.img_vehicle:
			intent = new Intent(this , SwipeActivitiy.class);
			intent.putExtra("category", "vehicle");
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
