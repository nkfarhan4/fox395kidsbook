package com.app.kidsbookapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.kidsbookapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import android.util.Log;
public class SwipeActivitiy extends Activity implements OnClickListener {

	private ImageView imageView_categoryimage , imageView_home ,  img_star_swipe;
	private TextView txHeadingItem , textView_categoryname;
	private RelativeLayout rel_touch;

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	private View.OnTouchListener gestureListener;
	private Bundle bundle;
	private String category , categoryName , swipeType;
	static MediaPlayer mp;
	int maincounter=0;
	InterstitialAd interstitial;
	com.google.android.gms.ads.AdRequest adRequest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe);

		bundle = getIntent().getExtras();
		if(bundle != null){
			category = bundle.getString("category");
		}

		interstitial = new InterstitialAd(SwipeActivitiy.this);
		interstitial.setAdUnitId("ca-app-pub-1878227272753934/9353348408");


		// Request for Ads
		adRequest = new com.google.android.gms.ads.AdRequest.Builder()
				.build();

		initView();
		onClickView();
		showAdd();
	}

	private void showAdd() {
		com.google.android.gms.ads.AdView adView = new com.google.android.gms.ads.AdView(this);
		adView.setAdUnitId("ca-app-pub-1878227272753934/5052100803");
		adView.setAdSize(AdSize.BANNER);
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.rel_bottom);        
		layout.addView(adView);
		com.google.android.gms.ads.AdRequest request = new com.google.android.gms.ads.AdRequest.Builder().build();
		adView.loadAd(request);
	}

	private void onClickView() {
		imageView_home.setOnClickListener(this);
		img_star_swipe.setOnClickListener(this);

	}

	private void initView() {
		imageView_categoryimage = (ImageView) findViewById(R.id.imageView_categoryimage);
		imageView_home = (ImageView) findViewById(R.id.imageView_home);
		txHeadingItem = (TextView) findViewById(R.id.txHeadingItem);
		textView_categoryname = (TextView) findViewById(R.id.textView_categoryname);
		img_star_swipe = (ImageView) findViewById(R.id.img_star_swipe);

		Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/" + "Hobo.ttf");
		textView_categoryname.setTypeface(tf);

		Typeface tff = Typeface.createFromAsset(getAssets(),"fonts/" + "Gabriela_Regular.ttf");
		txHeadingItem.setTypeface(tff);

		gestureDetector = new GestureDetector(this, new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		};	    
		rel_touch = (RelativeLayout) findViewById(R.id.rel_touch);
		rel_touch.setOnTouchListener(gestureListener);
		imageView_categoryimage.setOnTouchListener(gestureListener);


		if(category.equals("alphabet")){
			mp = MediaPlayer.create(this, R.raw.alphabets_apple);
			mp.start();
			categoryName = "apple";
			txHeadingItem.setText("Apple");
			imageView_categoryimage.setBackgroundResource(R.drawable.apple);
			textView_categoryname.setText("Alphabet");
		}else if(category.equals("number")){
			mp = MediaPlayer.create(this, R.raw.numbers_one);
			mp.start();
			categoryName = "one";
			txHeadingItem.setText("One");
			imageView_categoryimage.setBackgroundResource(R.drawable.one);
			textView_categoryname.setText("Number");
		}else if(category.equals("color")){

			mp = MediaPlayer.create(this, R.raw.colors_darkgreen);
			mp.start();
			categoryName = "darkgreen";
			txHeadingItem.setText("Dark Green");
			imageView_categoryimage.setBackgroundResource(R.drawable.darkgreen);
			textView_categoryname.setText("Color");
		}else if(category.equals("shapes")){
			mp = MediaPlayer.create(this, R.raw.shapes_square);
			mp.start();
			categoryName = "square";
			txHeadingItem.setText("Square");
			imageView_categoryimage.setBackgroundResource(R.drawable.square);
			textView_categoryname.setText("Shape");
		}else if(category.equals("vegetables")){
			mp = MediaPlayer.create(this, R.raw.vegetables_artichoke);
			mp.start();
			categoryName = "artichoke";
			txHeadingItem.setText("Artichoke");
			imageView_categoryimage.setBackgroundResource(R.drawable.artichoke);
			textView_categoryname.setText("Vegetable");
		}else if(category.equals("fruits")){
			mp = MediaPlayer.create(this, R.raw.fruits_apple);
			mp.start();
			categoryName = "apple";
			txHeadingItem.setText("Apple");
			imageView_categoryimage.setBackgroundResource(R.drawable.apple);
			textView_categoryname.setText("Fruits");
		}else if(category.equals("clothes")){
			mp = MediaPlayer.create(this, R.raw.clothes_coat);
			mp.start();
			categoryName = "coat";
			txHeadingItem.setText("Coat");
			imageView_categoryimage.setBackgroundResource(R.drawable.coat);
			textView_categoryname.setText("Clothes");
		}else if(category.equals("food")){
			mp = MediaPlayer.create(this, R.raw.food_applepie);
			mp.start();
			categoryName = "food_applepie";
			txHeadingItem.setText("Applepie");
			imageView_categoryimage.setBackgroundResource(R.drawable.food_applepie);
			textView_categoryname.setText("Food");
		}else if(category.equals("pob")){
			mp = MediaPlayer.create(this, R.raw.bodyparts_ankle);
			mp.start();
			categoryName = "ankle";
			txHeadingItem.setText("Ankle");
			imageView_categoryimage.setBackgroundResource(R.drawable.ankle);
			textView_categoryname.setText("Parts Of Body");
		}else if(category.equals("vehicle")){
			mp = MediaPlayer.create(this, R.raw.vehicles_aeroplane);
			mp.start();
			categoryName = "aeroplane";
			txHeadingItem.setText("Aeroplane");
			imageView_categoryimage.setBackgroundResource(R.drawable.aeroplane);
			textView_categoryname.setText("Vehicle");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView_home:
			finish();
			break;
		case R.id.img_star_swipe:
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
		default:
			break;
		}

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

	public void displayInterstitial() {
		// If Ads are loaded, show Interstitial else show nothing.
		if (interstitial.isLoaded()) {
			interstitial.show();
			//isAdLod=true;
		}
	}
	class MyGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//Toast.makeText(SwipeActivitiy.this, "Left Swipe", Toast.LENGTH_SHORT).show();

					swipeType = "Left";

					Log.e("swipw","left");
					maincounter+=1;
					if(maincounter==15) {
						// Load ads into Interstitial Ads
						interstitial.loadAd(adRequest);
						// Prepare an Interstitial Ad Listener
						interstitial.setAdListener(new AdListener() {
							public void onAdLoaded() {
								// Call displayInterstitial() function
								maincounter = 0;
								displayInterstitial();

							}
						});
					}



						if(category.equals("alphabet")){
						callAlphabet();
					}else if(category.equals("number")){
						callNumber();
					}else if(category.equals("color")){
						setColor();
					}else if(category.equals("shapes")){
						setShapes();
					}else if(category.equals("vegetables")){
						setVegetables();
					}else if(category.equals("fruits")){
						setFruits();
					}else if(category.equals("clothes")){
						setClothes();
					}else if(category.equals("food")){
						setFood();
					}else if(category.equals("pob")){
						setPob();
					}else if(category.equals("vehicle")){
						setVehicle();
					}

				}  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//Toast.makeText(SwipeActivitiy.this, "Right Swipe", Toast.LENGTH_SHORT).show();

					swipeType = "Right";
					Log.e("swipw","right");
					maincounter+=1;
					if(maincounter==15) {
							// Load ads into Interstitial Ads
							interstitial.loadAd(adRequest);
							// Prepare an Interstitial Ad Listener
							interstitial.setAdListener(new AdListener() {
								public void onAdLoaded() {
									// Call displayInterstitial() function
									maincounter = 0;
									displayInterstitial();

								}
							});
						}


							if(category.equals("alphabet")){
						callAlphabet();
					}else if(category.equals("number")){
						callNumber();
					}else if(category.equals("color")){
						setColor();
					}else if(category.equals("shapes")){
						setShapes();
					}else if(category.equals("vegetables")){
						setVegetables();
					}else if(category.equals("fruits")){
						setFruits();
					}else if(category.equals("clothes")){
						setClothes();
					}else if(category.equals("food")){
						setFood();
					}else if(category.equals("pob")){
						setPob();
					}else if(category.equals("vehicle")){
						setVehicle();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}
	}


	private void setVehicle(){
		if(categoryName.equals("aeroplane")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_ambulance);
				mp.start();
				categoryName = "ambulance";
				txHeadingItem.setText("Ambulance");
				imageView_categoryimage.setBackgroundResource(R.drawable.ambulance);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_truck);
				mp.start();
				categoryName = "truck";
				txHeadingItem.setText("Truck");
				imageView_categoryimage.setBackgroundResource(R.drawable.truck);
			}
		}else if(categoryName.equals("ambulance")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bicycle);
				mp.start();
				categoryName = "bicycle";
				txHeadingItem.setText("Bicycle");
				imageView_categoryimage.setBackgroundResource(R.drawable.bicycle);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_aeroplane);
				mp.start();
				categoryName = "aeroplane";
				txHeadingItem.setText("Aeroplane");
				imageView_categoryimage.setBackgroundResource(R.drawable.aeroplane);
			}
		}else if(categoryName.equals("bicycle")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bulldozer);
				mp.start();
				categoryName = "buildozer";
				txHeadingItem.setText("Buildozer");
				imageView_categoryimage.setBackgroundResource(R.drawable.bulldozer);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_ambulance);
				mp.start();
				categoryName = "ambulance";
				txHeadingItem.setText("Ambulance");
				imageView_categoryimage.setBackgroundResource(R.drawable.ambulance);
			}
		}else if(categoryName.equals("buildozer")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bus);
				mp.start();
				categoryName = "bus";
				txHeadingItem.setText("Bus");
				imageView_categoryimage.setBackgroundResource(R.drawable.bus);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bicycle);
				mp.start();
				categoryName = "bicycle";
				txHeadingItem.setText("Bicycle");
				imageView_categoryimage.setBackgroundResource(R.drawable.bicycle);
			}
		}else if(categoryName.equals("bus")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_car);
				mp.start();
				categoryName = "car";
				txHeadingItem.setText("Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.car);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bulldozer);
				mp.start();
				categoryName = "buildozer";
				txHeadingItem.setText("Buildozer");
				imageView_categoryimage.setBackgroundResource(R.drawable.bulldozer);
			}
		}else if(categoryName.equals("car")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_crane);
				mp.start();
				categoryName = "crane";
				txHeadingItem.setText("Crane");
				imageView_categoryimage.setBackgroundResource(R.drawable.crane);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_bus);
				mp.start();
				categoryName = "bus";
				txHeadingItem.setText("Bus");
				imageView_categoryimage.setBackgroundResource(R.drawable.bus);
			}
		}else if(categoryName.equals("crane")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_earthmover);
				mp.start();
				categoryName = "earthmover";
				txHeadingItem.setText("earthmover");
				imageView_categoryimage.setBackgroundResource(R.drawable.earthmover);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_car);
				mp.start();
				categoryName = "car";
				txHeadingItem.setText("Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.car);
			}
		}else if(categoryName.equals("earthmover")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_engine);
				mp.start();
				categoryName = "engine";
				txHeadingItem.setText("Engine");
				imageView_categoryimage.setBackgroundResource(R.drawable.engine);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_crane);
				mp.start();
				categoryName = "crane";
				txHeadingItem.setText("Crane");
				imageView_categoryimage.setBackgroundResource(R.drawable.crane);
			}
		}else if(categoryName.equals("engine")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_excavator);
				mp.start();
				categoryName = "excavator";
				txHeadingItem.setText("Excavator");
				imageView_categoryimage.setBackgroundResource(R.drawable.excavator);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_earthmover);
				mp.start();
				categoryName = "earthmover";
				txHeadingItem.setText("earthmover");
				imageView_categoryimage.setBackgroundResource(R.drawable.earthmover);
			}
		}else if(categoryName.equals("excavator")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_firetruck);
				mp.start();
				categoryName = "firetruck";
				txHeadingItem.setText("Fire Truck");
				imageView_categoryimage.setBackgroundResource(R.drawable.firetruck);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_engine);
				mp.start();
				categoryName = "engine";
				txHeadingItem.setText("Engine");
				imageView_categoryimage.setBackgroundResource(R.drawable.engine);
			}
		}else if(categoryName.equals("firetruck")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_helicopter);
				mp.start();
				categoryName = "helicopter";
				txHeadingItem.setText("Helicopter");
				imageView_categoryimage.setBackgroundResource(R.drawable.helicopter);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_excavator);
				mp.start();
				categoryName = "excavator";
				txHeadingItem.setText("Excavator");
				imageView_categoryimage.setBackgroundResource(R.drawable.excavator);
			}
		}else if(categoryName.equals("helicopter")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_hotairballoon);
				mp.start();
				categoryName = "hotairballon";
				txHeadingItem.setText("Hot Air Ballon");
				imageView_categoryimage.setBackgroundResource(R.drawable.hotairballoon);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_firetruck);
				mp.start();
				categoryName = "firetruck";
				txHeadingItem.setText("Fire Truck");
				imageView_categoryimage.setBackgroundResource(R.drawable.firetruck);
			}
		}else if(categoryName.equals("hotairballon")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_motorbike);
				mp.start();
				categoryName = "motorbike";
				txHeadingItem.setText("Motor Bike");
				imageView_categoryimage.setBackgroundResource(R.drawable.motorbike);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_helicopter);
				mp.start();
				categoryName = "helicopter";
				txHeadingItem.setText("Helicopter");
				imageView_categoryimage.setBackgroundResource(R.drawable.helicopter);
			}
		}else if(categoryName.equals("motorbike")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_parachute);
				mp.start();
				categoryName = "parachute";
				txHeadingItem.setText("Parachute");
				imageView_categoryimage.setBackgroundResource(R.drawable.parachute);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_hotairballoon);
				mp.start();
				categoryName = "hotairballon";
				txHeadingItem.setText("Hot Air Ballon");
				imageView_categoryimage.setBackgroundResource(R.drawable.hotairballoon);
			}
		}else if(categoryName.equals("parachute")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_policecar);
				mp.start();
				categoryName = "policecar";
				txHeadingItem.setText("Police Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.policecar);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_motorbike);
				mp.start();
				categoryName = "motorbike";
				txHeadingItem.setText("Motor Bike");
				imageView_categoryimage.setBackgroundResource(R.drawable.motorbike);
			}
		}else if(categoryName.equals("policecar")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_racecar);
				mp.start();
				categoryName = "racecar";
				txHeadingItem.setText("Race Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.racecar);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_parachute);
				mp.start();
				categoryName = "parachute";
				txHeadingItem.setText("Parachute");
				imageView_categoryimage.setBackgroundResource(R.drawable.parachute);
			}
		}else if(categoryName.equals("racecar")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_schoolbus);
				mp.start();
				categoryName = "schoolbus";
				txHeadingItem.setText("School Bus");
				imageView_categoryimage.setBackgroundResource(R.drawable.schoolbus);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_policecar);
				mp.start();
				categoryName = "policecar";
				txHeadingItem.setText("Police Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.policecar);
			}
		}else if(categoryName.equals("schoolbus")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_ship);
				mp.start();
				categoryName = "ship";
				txHeadingItem.setText("Ship");
				imageView_categoryimage.setBackgroundResource(R.drawable.ship);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_racecar);
				mp.start();
				categoryName = "racecar";
				txHeadingItem.setText("Race Car");
				imageView_categoryimage.setBackgroundResource(R.drawable.racecar);
			}
		}else if(categoryName.equals("ship")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_submarine);
				mp.start();
				categoryName = "submarine";
				txHeadingItem.setText("Submarine");
				imageView_categoryimage.setBackgroundResource(R.drawable.submarine);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_schoolbus);
				mp.start();
				categoryName = "schoolbus";
				txHeadingItem.setText("School Bus");
				imageView_categoryimage.setBackgroundResource(R.drawable.schoolbus);
			}
		}else if(categoryName.equals("submarine")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_tractor);
				mp.start();
				categoryName = "tractor";
				txHeadingItem.setText("Tractor");
				imageView_categoryimage.setBackgroundResource(R.drawable.tractor);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_ship);
				mp.start();
				categoryName = "ship";
				txHeadingItem.setText("Ship");
				imageView_categoryimage.setBackgroundResource(R.drawable.ship);
			}
		}else if(categoryName.equals("tractor")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_train);
				mp.start();
				categoryName = "train";
				txHeadingItem.setText("Train");
				imageView_categoryimage.setBackgroundResource(R.drawable.train);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_submarine);
				mp.start();
				categoryName = "submarine";
				txHeadingItem.setText("Submarine");
				imageView_categoryimage.setBackgroundResource(R.drawable.submarine);
			}
		}else if(categoryName.equals("train")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_tram);
				mp.start();
				categoryName = "tram";
				txHeadingItem.setText("Tram");
				imageView_categoryimage.setBackgroundResource(R.drawable.tram);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_tractor);
				mp.start();
				categoryName = "tractor";
				txHeadingItem.setText("Tractor");
				imageView_categoryimage.setBackgroundResource(R.drawable.tractor);
			}
		}else if(categoryName.equals("tram")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_truck);
				mp.start();
				categoryName = "truck";
				txHeadingItem.setText("truck");
				imageView_categoryimage.setBackgroundResource(R.drawable.truck);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_train);
				mp.start();
				categoryName = "train";
				txHeadingItem.setText("Train");
				imageView_categoryimage.setBackgroundResource(R.drawable.train);
			}
		}else if(categoryName.equals("truck")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_aeroplane);
				mp.start();
				categoryName = "aeroplane";
				txHeadingItem.setText("Aeroplane");
				imageView_categoryimage.setBackgroundResource(R.drawable.aeroplane);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vehicles_tram);
				mp.start();
				categoryName = "tram";
				txHeadingItem.setText("Tram");
				imageView_categoryimage.setBackgroundResource(R.drawable.tram);
			}

		}
	}

	private void setPob(){

		if(categoryName.equals("ankle")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_arm);
				mp.start();
				categoryName = "arm";
				txHeadingItem.setText("Arm");
				imageView_categoryimage.setBackgroundResource(R.drawable.arm);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_tongue);
				mp.start();
				categoryName = "tounge";
				txHeadingItem.setText("Tounge");
				imageView_categoryimage.setBackgroundResource(R.drawable.tongue);
			}
		}else if(categoryName.equals("arm")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_back);
				mp.start();
				categoryName = "back";
				txHeadingItem.setText("Back");
				imageView_categoryimage.setBackgroundResource(R.drawable.back);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_ankle);
				mp.start();
				categoryName = "ankle";
				txHeadingItem.setText("Ankle");
				imageView_categoryimage.setBackgroundResource(R.drawable.ankle);
			}
		}else if(categoryName.equals("back")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_chest);
				mp.start();
				categoryName = "chest";
				txHeadingItem.setText("Chest");
				imageView_categoryimage.setBackgroundResource(R.drawable.chest);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_arm);
				mp.start();
				categoryName = "arm";
				txHeadingItem.setText("Arm");
				imageView_categoryimage.setBackgroundResource(R.drawable.arm);
			}
		}else if(categoryName.equals("chest")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_chin);
				mp.start();
				categoryName = "chin";
				txHeadingItem.setText("Chin");
				imageView_categoryimage.setBackgroundResource(R.drawable.chin);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_back);
				mp.start();
				categoryName = "back";
				txHeadingItem.setText("Back");
				imageView_categoryimage.setBackgroundResource(R.drawable.back);
			}
		}else if(categoryName.equals("chin")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_ears);
				mp.start();
				categoryName = "ears";
				txHeadingItem.setText("Ears");
				imageView_categoryimage.setBackgroundResource(R.drawable.ears);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_chest);
				mp.start();
				categoryName = "chest";
				txHeadingItem.setText("Chest");
				imageView_categoryimage.setBackgroundResource(R.drawable.chest);
			}
		}else if(categoryName.equals("ears")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_elbow);
				mp.start();
				categoryName = "elbow";
				txHeadingItem.setText("Elbow");
				imageView_categoryimage.setBackgroundResource(R.drawable.elbow);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_chin);
				mp.start();
				categoryName = "chin";
				txHeadingItem.setText("Chin");
				imageView_categoryimage.setBackgroundResource(R.drawable.chin);
			}
		}else if(categoryName.equals("elbow")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_eyebrow);
				mp.start();
				categoryName = "eyebrow";
				txHeadingItem.setText("Eyebrow");
				imageView_categoryimage.setBackgroundResource(R.drawable.eyebrow);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_ears);
				mp.start();
				categoryName = "ears";
				txHeadingItem.setText("Ears");
				imageView_categoryimage.setBackgroundResource(R.drawable.ears);
			}
		}else if(categoryName.equals("eyebrow")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_eyes);
				mp.start();
				categoryName = "eyes";
				txHeadingItem.setText("Eyes");
				imageView_categoryimage.setBackgroundResource(R.drawable.eyes);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_elbow);
				mp.start();
				categoryName = "elbow";
				txHeadingItem.setText("Elbow");
				imageView_categoryimage.setBackgroundResource(R.drawable.elbow);
			}
		}else if(categoryName.equals("eyes")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_face);
				mp.start();
				categoryName = "face";
				txHeadingItem.setText("Face");
				imageView_categoryimage.setBackgroundResource(R.drawable.face);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_eyebrow);
				mp.start();
				categoryName = "eyebrow";
				txHeadingItem.setText("Eyebrow");
				imageView_categoryimage.setBackgroundResource(R.drawable.eyebrow);
			}
		}else if(categoryName.equals("face")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_feet);
				mp.start();
				categoryName = "feet";
				txHeadingItem.setText("Feet");
				imageView_categoryimage.setBackgroundResource(R.drawable.feet);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_eyes);
				mp.start();
				categoryName = "eyes";
				txHeadingItem.setText("Eyes");
				imageView_categoryimage.setBackgroundResource(R.drawable.eyes);
			}
		}else if(categoryName.equals("feet")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_fingers);
				mp.start();
				categoryName = "fingers";
				txHeadingItem.setText("Fingers");
				imageView_categoryimage.setBackgroundResource(R.drawable.fingers);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_face);
				mp.start();
				categoryName = "face";
				txHeadingItem.setText("Face");
				imageView_categoryimage.setBackgroundResource(R.drawable.face);
			}
		}else if(categoryName.equals("fingers")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hair);
				mp.start();
				categoryName = "hair";
				txHeadingItem.setText("Hair");
				imageView_categoryimage.setBackgroundResource(R.drawable.hair);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_feet);
				mp.start();
				categoryName = "feet";
				txHeadingItem.setText("Feet");
				imageView_categoryimage.setBackgroundResource(R.drawable.feet);
			}
		}else if(categoryName.equals("hair")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hand);
				mp.start();
				categoryName = "hand";
				txHeadingItem.setText("Hand");
				imageView_categoryimage.setBackgroundResource(R.drawable.hand);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_fingers);
				mp.start();
				categoryName = "fingers";
				txHeadingItem.setText("Fingers");
				imageView_categoryimage.setBackgroundResource(R.drawable.fingers);
			}
		}else if(categoryName.equals("hand")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_head);
				mp.start();
				categoryName = "head";
				txHeadingItem.setText("Head");
				imageView_categoryimage.setBackgroundResource(R.drawable.head);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hair);
				mp.start();
				categoryName = "hair";
				txHeadingItem.setText("Hair");
				imageView_categoryimage.setBackgroundResource(R.drawable.hair);
			}
		}else if(categoryName.equals("head")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hip);
				mp.start();
				categoryName = "hip";
				txHeadingItem.setText("Hip");
				imageView_categoryimage.setBackgroundResource(R.drawable.hip);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hand);
				mp.start();
				categoryName = "hand";
				txHeadingItem.setText("Hand");
				imageView_categoryimage.setBackgroundResource(R.drawable.hand);
			}
		}else if(categoryName.equals("hip")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_knee);
				mp.start();
				categoryName = "knee";
				txHeadingItem.setText("Knee");
				imageView_categoryimage.setBackgroundResource(R.drawable.knee);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_head);
				mp.start();
				categoryName = "head";
				txHeadingItem.setText("Head");
				imageView_categoryimage.setBackgroundResource(R.drawable.head);
			}
		}else if(categoryName.equals("knee")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_lips);
				mp.start();
				categoryName = "lips";
				txHeadingItem.setText("Lips");
				imageView_categoryimage.setBackgroundResource(R.drawable.lips);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_hip);
				mp.start();
				categoryName = "hip";
				txHeadingItem.setText("Hip");
				imageView_categoryimage.setBackgroundResource(R.drawable.hip);
			}
		}else if(categoryName.equals("lips")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_mouth);
				mp.start();
				categoryName = "mouth";
				txHeadingItem.setText("Mouth");
				imageView_categoryimage.setBackgroundResource(R.drawable.mouth);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_knee);
				mp.start();
				categoryName = "knee";
				txHeadingItem.setText("Knee");
				imageView_categoryimage.setBackgroundResource(R.drawable.knee);
			}
		}else if(categoryName.equals("mouth")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_neck);
				mp.start();
				categoryName = "neck";
				txHeadingItem.setText("Neck");
				imageView_categoryimage.setBackgroundResource(R.drawable.neck);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_lips);
				mp.start();
				categoryName = "lips";
				txHeadingItem.setText("Lips");
				imageView_categoryimage.setBackgroundResource(R.drawable.lips);
			}
		}else if(categoryName.equals("neck")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_nose);
				mp.start();
				categoryName = "nose";
				txHeadingItem.setText("Nose");
				imageView_categoryimage.setBackgroundResource(R.drawable.nose);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_mouth);
				mp.start();
				categoryName = "mouth";
				txHeadingItem.setText("Mouth");
				imageView_categoryimage.setBackgroundResource(R.drawable.mouth);
			}
		}else if(categoryName.equals("nose")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_shoulder);
				mp.start();
				categoryName = "shoulder";
				txHeadingItem.setText("Shoulder");
				imageView_categoryimage.setBackgroundResource(R.drawable.shoulder);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_neck);
				mp.start();
				categoryName = "neck";
				txHeadingItem.setText("Neck");
				imageView_categoryimage.setBackgroundResource(R.drawable.neck);
			}
		}else if(categoryName.equals("shoulder")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_teeth);
				mp.start();
				categoryName = "teeth";
				txHeadingItem.setText("Teeth");
				imageView_categoryimage.setBackgroundResource(R.drawable.teeth);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_nose);
				mp.start();
				categoryName = "nose";
				txHeadingItem.setText("Nose");
				imageView_categoryimage.setBackgroundResource(R.drawable.nose);
			}
		}else if(categoryName.equals("teeth")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_tongue);
				mp.start();
				categoryName = "tounge";
				txHeadingItem.setText("Tounge");
				imageView_categoryimage.setBackgroundResource(R.drawable.tongue);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_shoulder);
				mp.start();
				categoryName = "shoulder";
				txHeadingItem.setText("Shoulder");
				imageView_categoryimage.setBackgroundResource(R.drawable.shoulder);
			}
		}else if(categoryName.equals("tounge")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_ankle);
				mp.start();
				categoryName = "ankle";
				txHeadingItem.setText("Ankle");
				imageView_categoryimage.setBackgroundResource(R.drawable.ankle);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.bodyparts_teeth);
				mp.start();
				categoryName = "teeth";
				txHeadingItem.setText("Teeth");
				imageView_categoryimage.setBackgroundResource(R.drawable.teeth);
			}
		}

	}

	private void setFood(){
		if(categoryName.equals("food_applepie")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_burger);
				mp.start();
				categoryName = "food_burger";
				txHeadingItem.setText("Burger");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_burger);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_vegetables);
				mp.start();
				categoryName = "food_vegetables";
				txHeadingItem.setText("Vegetables");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_vegetables);
			}
		}else if(categoryName.equals("food_burger")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_butter);
				mp.start();
				categoryName = "food_butter";
				txHeadingItem.setText("Butter");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_butter);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_applepie);
				mp.start();
				categoryName = "food_applepie";
				txHeadingItem.setText("Applepie");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_applepie);
			}
		}else if(categoryName.equals("food_butter")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cake);
				mp.start();
				categoryName = "food_cake";
				txHeadingItem.setText("Cake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cake);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_burger);
				mp.start();
				categoryName = "food_burger";
				txHeadingItem.setText("Burger");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_burger);
			}
		}else if(categoryName.equals("food_cake")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_candy);
				mp.start();
				categoryName = "food_candy";
				txHeadingItem.setText("Candy");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_candy);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_butter);
				mp.start();
				categoryName = "food_butter";
				txHeadingItem.setText("Butter");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_butter);
			}
		}else if(categoryName.equals("food_candy")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cheese);
				mp.start();
				categoryName = "food_cheese";
				txHeadingItem.setText("Cheese");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cheese);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cake);
				mp.start();
				categoryName = "food_cake";
				txHeadingItem.setText("Cake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cake);
			}
		}else if(categoryName.equals("food_cheese")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_chocolate);
				mp.start();
				categoryName = "food_chocolate";
				txHeadingItem.setText("Chocolate");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_chocolate);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_candy);
				mp.start();
				categoryName = "food_candy";
				txHeadingItem.setText("Candy");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_candy);
			}
		}else if(categoryName.equals("food_chocolate")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_coffee);
				mp.start();
				categoryName = "food_coffee";
				txHeadingItem.setText("Coffee");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_coffee);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cheese);
				mp.start();
				categoryName = "food_cheese";
				txHeadingItem.setText("Cheese");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cheese);
			}
		}else if(categoryName.equals("food_coffee")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cookies);
				mp.start();
				categoryName = "food_cookies";
				txHeadingItem.setText("Cookies");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cookies);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_chocolate);
				mp.start();
				categoryName = "food_chocolate";
				txHeadingItem.setText("Chocolate");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_chocolate);
			}
		}else if(categoryName.equals("food_cookies")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cupcake);
				mp.start();
				categoryName = "food_cupcake";
				txHeadingItem.setText("Cupcake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cupcake);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_coffee);
				mp.start();
				categoryName = "food_coffee";
				txHeadingItem.setText("Coffee");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_coffee);
			}
		}else if(categoryName.equals("food_cupcake")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_donut);
				mp.start();
				categoryName = "food_donut";
				txHeadingItem.setText("Donut");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_donut);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cookies);
				mp.start();
				categoryName = "food_cookies";
				txHeadingItem.setText("Cookies");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cookies);
			}
		}else if(categoryName.equals("food_donut")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fries);
				mp.start();
				categoryName = "food_fries";
				txHeadingItem.setText("Fries");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fries);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_cupcake);
				mp.start();
				categoryName = "food_cupcake";
				txHeadingItem.setText("Cupcake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_cupcake);
			}
		}else if(categoryName.equals("food_fries")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fruits);
				mp.start();
				categoryName = "food_fruits";
				txHeadingItem.setText("Fruits");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fruits);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_donut);
				mp.start();
				categoryName = "food_donut";
				txHeadingItem.setText("Donut");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_donut);
			}
		}else if(categoryName.equals("food_fruits")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fruitsalad);
				mp.start();
				categoryName = "food_fruitsalad";
				txHeadingItem.setText("Fruit Salad");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fruitsalad);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fries);
				mp.start();
				categoryName = "food_fries";
				txHeadingItem.setText("Fries");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fries);
			}
		}else if(categoryName.equals("food_fruitsalad")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_icecream);
				mp.start();
				categoryName = "food_icecream";
				txHeadingItem.setText("Icecream");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_icecream);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fruits);
				mp.start();
				categoryName = "food_fruits";
				txHeadingItem.setText("Fruits");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fruits);
			}
		}else if(categoryName.equals("food_icecream")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_jelly);
				mp.start();
				categoryName = "food_jelly";
				txHeadingItem.setText("Jelly");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_jelly);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_fruitsalad);
				mp.start();
				categoryName = "food_fruitsalad";
				txHeadingItem.setText("Fruit Salad");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_fruitsalad);
			}
		}else if(categoryName.equals("food_jelly")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_juice);
				mp.start();
				categoryName = "food_juice";
				txHeadingItem.setText("Juice");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_juice);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_icecream);
				mp.start();
				categoryName = "food_icecream";
				txHeadingItem.setText("Icecream");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_icecream);
			}
		}else if(categoryName.equals("food_juice")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_milkshake);
				mp.start();
				categoryName = "food_milkshake";
				txHeadingItem.setText("Milkshake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_milkshake);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_jelly);
				mp.start();
				categoryName = "food_jelly";
				txHeadingItem.setText("Jelly");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_jelly);
			}
		}else if(categoryName.equals("food_milkshake")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_muffin);
				mp.start();
				categoryName = "food_muffin";
				txHeadingItem.setText("Muffin");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_muffin);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_juice);
				mp.start();
				categoryName = "food_juice";
				txHeadingItem.setText("Juice");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_juice);
			}
		}else if(categoryName.equals("food_muffin")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pancake);
				mp.start();
				categoryName = "food_pancake";
				txHeadingItem.setText("Pancake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pancake);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_milkshake);
				mp.start();
				categoryName = "food_milkshake";
				txHeadingItem.setText("Milkshake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_milkshake);
			}
		}else if(categoryName.equals("food_pancake")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pasta);
				mp.start();
				categoryName = "food_pasta";
				txHeadingItem.setText("Pasta");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pasta);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_muffin);
				mp.start();
				categoryName = "food_muffin";
				txHeadingItem.setText("Muffin");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_muffin);
			}
		}else if(categoryName.equals("food_pasta")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pizza);
				mp.start();
				categoryName = "food_pizza";
				txHeadingItem.setText("Pizza");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pizza);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pancake);
				mp.start();
				categoryName = "food_pancake";
				txHeadingItem.setText("Pancake");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pancake);
			}
		}else if(categoryName.equals("food_pizza")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pudding);
				mp.start();
				categoryName = "food_pudding";
				txHeadingItem.setText("Pudding");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pudding);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pasta);
				mp.start();
				categoryName = "food_pasta";
				txHeadingItem.setText("Pasta");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pasta);
			}
		}else if(categoryName.equals("food_pudding")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_salad);
				mp.start();
				categoryName = "food_salad";
				txHeadingItem.setText("Salad");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_salad);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pizza);
				mp.start();
				categoryName = "food_pizza";
				txHeadingItem.setText("Pizza");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pizza);
			}
		}else if(categoryName.equals("food_salad")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_sandwich);
				mp.start();
				categoryName = "food_sandwich";
				txHeadingItem.setText("Sandwich");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_sandwich);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_pudding);
				mp.start();
				categoryName = "food_pudding";
				txHeadingItem.setText("Pudding");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_pudding);
			}
		}else if(categoryName.equals("food_sandwich")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_vegetables);
				mp.start();
				categoryName = "food_vegetables";
				txHeadingItem.setText("Vegetables");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_vegetables);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_salad);
				mp.start();
				categoryName = "food_salad";
				txHeadingItem.setText("Salad");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_salad);
			}
		}else if(categoryName.equals("food_vegetables")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_applepie);
				mp.start();
				categoryName = "food_applepie";
				txHeadingItem.setText("Applepie");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_applepie);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.food_sandwich);
				mp.start();
				categoryName = "food_sandwich";
				txHeadingItem.setText("Sandwich");
				imageView_categoryimage.setBackgroundResource(R.drawable.food_sandwich);
			}
		}
	} 

	private void setClothes() {
		if(categoryName.equals("coat")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_dress);
				mp.start();
				categoryName = "dress";
				txHeadingItem.setText("Dress");
				imageView_categoryimage.setBackgroundResource(R.drawable.dress);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_cap);
				mp.start();
				categoryName = "cap";
				txHeadingItem.setText("Cap");
				imageView_categoryimage.setBackgroundResource(R.drawable.cap);
			}
		}else if(categoryName.equals("dress")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_frock);
				mp.start();
				categoryName = "frock";
				txHeadingItem.setText("Frock");
				imageView_categoryimage.setBackgroundResource(R.drawable.frock);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_coat);
				mp.start();
				categoryName = "coat";
				txHeadingItem.setText("Coat");
				imageView_categoryimage.setBackgroundResource(R.drawable.coat);
			}
		}else if(categoryName.equals("frock")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_gloves);
				mp.start();
				categoryName = "gloves";
				txHeadingItem.setText("Gloves");
				imageView_categoryimage.setBackgroundResource(R.drawable.gloves);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_dress);
				mp.start();
				categoryName = "dress";
				txHeadingItem.setText("Dress");
				imageView_categoryimage.setBackgroundResource(R.drawable.dress);
			}
		}else if(categoryName.equals("gloves")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_jacket);
				mp.start();
				categoryName = "jacket";
				txHeadingItem.setText("Jacket");
				imageView_categoryimage.setBackgroundResource(R.drawable.jacket);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_frock);
				mp.start();
				categoryName = "frock";
				txHeadingItem.setText("Frock");
				imageView_categoryimage.setBackgroundResource(R.drawable.frock);
			}
		}else if(categoryName.equals("jacket")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_mittens);
				mp.start();
				categoryName = "mittens";
				txHeadingItem.setText("Mittens");
				imageView_categoryimage.setBackgroundResource(R.drawable.mittens);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_gloves);
				mp.start();
				categoryName = "gloves";
				txHeadingItem.setText("Gloves");
				imageView_categoryimage.setBackgroundResource(R.drawable.gloves);
			}
		}else if(categoryName.equals("mittens")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_pajamas);
				mp.start();
				categoryName = "pajamas";
				txHeadingItem.setText("Pajamas");
				imageView_categoryimage.setBackgroundResource(R.drawable.pajamas);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_jacket);
				mp.start();
				categoryName = "jacket";
				txHeadingItem.setText("Jacket");
				imageView_categoryimage.setBackgroundResource(R.drawable.jacket);
			}
		}else if(categoryName.equals("pajamas")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_pant);
				mp.start();
				categoryName = "pant";
				txHeadingItem.setText("Pant");
				imageView_categoryimage.setBackgroundResource(R.drawable.pant);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_mittens);
				mp.start();
				categoryName = "mittens";
				txHeadingItem.setText("Mittens");
				imageView_categoryimage.setBackgroundResource(R.drawable.mittens);
			}
		}else if(categoryName.equals("pant")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_saree);
				mp.start();
				categoryName = "saree";
				txHeadingItem.setText("Saree");
				imageView_categoryimage.setBackgroundResource(R.drawable.saree);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_pajamas);
				mp.start();
				categoryName = "pajamas";
				txHeadingItem.setText("Pajamas");
				imageView_categoryimage.setBackgroundResource(R.drawable.pajamas);
			}
		}else if(categoryName.equals("saree")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_scarf);
				mp.start();
				categoryName = "scarf";
				txHeadingItem.setText("Scarf");
				imageView_categoryimage.setBackgroundResource(R.drawable.scarf);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_pant);
				mp.start();
				categoryName = "pant";
				txHeadingItem.setText("Pant");
				imageView_categoryimage.setBackgroundResource(R.drawable.pant);
			}
		}else if(categoryName.equals("scarf")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_shirt);
				mp.start();
				categoryName = "shirt";
				txHeadingItem.setText("Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.shirt);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_saree);
				mp.start();
				categoryName = "saree";
				txHeadingItem.setText("Saree");
				imageView_categoryimage.setBackgroundResource(R.drawable.saree);
			}
		}else if(categoryName.equals("shirt")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_shorts);
				mp.start();
				categoryName = "shorts";
				txHeadingItem.setText("Shorts");
				imageView_categoryimage.setBackgroundResource(R.drawable.shorts);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_scarf);
				mp.start();
				categoryName = "scarf";
				txHeadingItem.setText("Scarf");
				imageView_categoryimage.setBackgroundResource(R.drawable.scarf);
			}
		}else if(categoryName.equals("shorts")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_skirt);
				mp.start();
				categoryName = "skirt";
				txHeadingItem.setText("Skirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.skirt);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_shirt);
				mp.start();
				categoryName = "shirt";
				txHeadingItem.setText("Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.shirt);
			}
		}else if(categoryName.equals("skirt")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_socks);
				mp.start();
				categoryName = "socks";
				txHeadingItem.setText("Socks");
				imageView_categoryimage.setBackgroundResource(R.drawable.socks);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_shorts);
				mp.start();
				categoryName = "shorts";
				txHeadingItem.setText("Shorts");
				imageView_categoryimage.setBackgroundResource(R.drawable.shorts);
			}
		}else if(categoryName.equals("socks")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_sweater);
				mp.start();
				categoryName = "sweater";
				txHeadingItem.setText("Sweater");
				imageView_categoryimage.setBackgroundResource(R.drawable.sweater);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_skirt);
				mp.start();
				categoryName = "skirt";
				txHeadingItem.setText("Skirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.skirt);
			}
		}else if(categoryName.equals("sweater")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_sweatshirt);
				mp.start();
				categoryName = "sweatshirt";
				txHeadingItem.setText("Sweat Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.sweatshirt);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_socks);
				mp.start();
				categoryName = "socks";
				txHeadingItem.setText("Socks");
				imageView_categoryimage.setBackgroundResource(R.drawable.socks);
			}
		}else if(categoryName.equals("sweatshirt")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_tie);
				mp.start();
				categoryName = "tie";
				txHeadingItem.setText("Tie");
				imageView_categoryimage.setBackgroundResource(R.drawable.tie);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_sweater);
				mp.start();
				categoryName = "sweater";
				txHeadingItem.setText("Sweater");
				imageView_categoryimage.setBackgroundResource(R.drawable.sweater);
			}
		}else if(categoryName.equals("tie")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_tshirt);
				mp.start();
				categoryName = "tshirt";
				txHeadingItem.setText("T-Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.tshirt);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_sweatshirt);
				mp.start();
				categoryName = "sweatshirt";
				txHeadingItem.setText("Sweat Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.sweatshirt);
			}
		}else if(categoryName.equals("tshirt")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_bathingsuit);
				mp.start();
				categoryName = "bathingsuit";
				txHeadingItem.setText("Bathing Suit");
				imageView_categoryimage.setBackgroundResource(R.drawable.bathingsuit);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_tie);
				mp.start();
				categoryName = "tie";
				txHeadingItem.setText("Tie");
				imageView_categoryimage.setBackgroundResource(R.drawable.tie);
			}
		}else if(categoryName.equals("bathingsuit")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_belt);
				mp.start();
				categoryName = "belt";
				txHeadingItem.setText("Belt");
				imageView_categoryimage.setBackgroundResource(R.drawable.belt);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_tshirt);
				mp.start();
				categoryName = "tshirt";
				txHeadingItem.setText("T-Shirt");
				imageView_categoryimage.setBackgroundResource(R.drawable.tshirt);
			}
		}else if(categoryName.equals("belt")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_blouse);
				mp.start();
				categoryName = "blouse";
				txHeadingItem.setText("Blouse");
				imageView_categoryimage.setBackgroundResource(R.drawable.blouse);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_bathingsuit);
				mp.start();
				categoryName = "bathingsuit";
				txHeadingItem.setText("Bathing Suit");
				imageView_categoryimage.setBackgroundResource(R.drawable.bathingsuit);
			}
		}else if(categoryName.equals("blouse")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_boots);
				mp.start();
				categoryName = "boots";
				txHeadingItem.setText("Boots");
				imageView_categoryimage.setBackgroundResource(R.drawable.boots);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_belt);
				mp.start();
				categoryName = "belt";
				txHeadingItem.setText("Belt");
				imageView_categoryimage.setBackgroundResource(R.drawable.belt);
			}
		}else if(categoryName.equals("boots")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_businesssuit);
				mp.start();
				categoryName = "buisenesssuit";
				txHeadingItem.setText("Business Suit");
				imageView_categoryimage.setBackgroundResource(R.drawable.businesssuit);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_blouse);
				mp.start();
				categoryName = "blouse";
				txHeadingItem.setText("Blouse");
				imageView_categoryimage.setBackgroundResource(R.drawable.blouse);
			}
		}else if(categoryName.equals("buisenesssuit")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_cap);
				mp.start();
				categoryName = "cap";
				txHeadingItem.setText("Cap");
				imageView_categoryimage.setBackgroundResource(R.drawable.cap);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_boots);
				mp.start();
				categoryName = "boots";
				txHeadingItem.setText("Boots");
				imageView_categoryimage.setBackgroundResource(R.drawable.boots);
			}
		}else if(categoryName.equals("cap")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_coat);
				mp.start();
				categoryName = "coat";
				txHeadingItem.setText("Coat");
				imageView_categoryimage.setBackgroundResource(R.drawable.coat);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.clothes_businesssuit);
				mp.start();
				categoryName = "buisenesssuit";
				txHeadingItem.setText("Business Suit");
				imageView_categoryimage.setBackgroundResource(R.drawable.businesssuit);
			}
		}

	}


	private void setFruits(){
		if(categoryName.equals("apple")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_apricot);
				mp.start();
				categoryName = "apricot";
				txHeadingItem.setText("Apricot");
				imageView_categoryimage.setBackgroundResource(R.drawable.apricot);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_watermelon);
				mp.start();
				categoryName = "watermelon";
				txHeadingItem.setText("Watermelon");
				imageView_categoryimage.setBackgroundResource(R.drawable.watermelon);
			}
		}else if(categoryName.equals("apricot")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_avocado);
				mp.start();
				categoryName = "avocado";
				txHeadingItem.setText("Avocado");
				imageView_categoryimage.setBackgroundResource(R.drawable.avocado);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_apple);
				mp.start();
				categoryName = "apple";
				txHeadingItem.setText("Apple");
				imageView_categoryimage.setBackgroundResource(R.drawable.apple);
			}
		}else if(categoryName.equals("avocado")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_banana);
				mp.start();
				categoryName = "banana";
				txHeadingItem.setText("Banana");
				imageView_categoryimage.setBackgroundResource(R.drawable.banana);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_apricot);
				mp.start();
				categoryName = "apricot";
				txHeadingItem.setText("Apricot");
				imageView_categoryimage.setBackgroundResource(R.drawable.apricot);
			}
		}else if(categoryName.equals("banana")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_cherries);
				mp.start();
				categoryName = "cherries";
				txHeadingItem.setText("Cherries");
				imageView_categoryimage.setBackgroundResource(R.drawable.cherries);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_avocado);
				mp.start();
				categoryName = "avocado";
				txHeadingItem.setText("Avocado");
				imageView_categoryimage.setBackgroundResource(R.drawable.avocado);
			}
		}else if(categoryName.equals("cherries")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_grapes);
				mp.start();
				categoryName = "grapes";
				txHeadingItem.setText("Grapes");
				imageView_categoryimage.setBackgroundResource(R.drawable.grapes);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_banana);
				mp.start();
				categoryName = "banana";
				txHeadingItem.setText("Banana");
				imageView_categoryimage.setBackgroundResource(R.drawable.banana);
			}
		}else if(categoryName.equals("grapes")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_guava);
				mp.start();
				categoryName = "guava";
				txHeadingItem.setText("Guava");
				imageView_categoryimage.setBackgroundResource(R.drawable.guava);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_cherries);
				mp.start();
				categoryName = "cherries";
				txHeadingItem.setText("Cherries");
				imageView_categoryimage.setBackgroundResource(R.drawable.cherries);
			}
		}else if(categoryName.equals("guava")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_kiwi);
				mp.start();
				categoryName = "kiwi";
				txHeadingItem.setText("Kiwi");
				imageView_categoryimage.setBackgroundResource(R.drawable.kiwi);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_grapes);
				mp.start();
				categoryName = "grapes";
				txHeadingItem.setText("Grapes");
				imageView_categoryimage.setBackgroundResource(R.drawable.grapes);
			}
		}else if(categoryName.equals("kiwi")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_lemon);
				mp.start();
				categoryName = "lemon";
				txHeadingItem.setText("Lemon");
				imageView_categoryimage.setBackgroundResource(R.drawable.lemon);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_guava);
				mp.start();
				categoryName = "guava";
				txHeadingItem.setText("Guava");
				imageView_categoryimage.setBackgroundResource(R.drawable.guava);
			}
		}else if(categoryName.equals("lemon")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_litchi);
				mp.start();
				categoryName = "litchi";
				txHeadingItem.setText("Litchi");
				imageView_categoryimage.setBackgroundResource(R.drawable.litchi);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_kiwi);
				mp.start();
				categoryName = "kiwi";
				txHeadingItem.setText("Kiwi");
				imageView_categoryimage.setBackgroundResource(R.drawable.kiwi);
			}
		}else if(categoryName.equals("litchi")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_mango);
				mp.start();
				categoryName = "mango";
				txHeadingItem.setText("Mango");
				imageView_categoryimage.setBackgroundResource(R.drawable.mango);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_lemon);
				mp.start();
				categoryName = "lemon";
				txHeadingItem.setText("Lemon");
				imageView_categoryimage.setBackgroundResource(R.drawable.lemon);
			}
		}else if(categoryName.equals("mango")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_orange);
				mp.start();
				categoryName = "orange";
				txHeadingItem.setText("Orange");
				imageView_categoryimage.setBackgroundResource(R.drawable.orange);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_litchi);
				mp.start();
				categoryName = "litchi";
				txHeadingItem.setText("Litchi");
				imageView_categoryimage.setBackgroundResource(R.drawable.litchi);
			}
		}else if(categoryName.equals("orange")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_peach);
				mp.start();
				categoryName = "peach";
				txHeadingItem.setText("Peach");
				imageView_categoryimage.setBackgroundResource(R.drawable.peach);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_mango);
				mp.start();
				categoryName = "mango";
				txHeadingItem.setText("Mango");
				imageView_categoryimage.setBackgroundResource(R.drawable.mango);
			}
		}else if(categoryName.equals("peach")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pear);
				mp.start();
				categoryName = "pear";
				txHeadingItem.setText("Pear");
				imageView_categoryimage.setBackgroundResource(R.drawable.pear);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_orange);
				mp.start();
				categoryName = "orange";
				txHeadingItem.setText("Orange");
				imageView_categoryimage.setBackgroundResource(R.drawable.orange);
			}
		}else if(categoryName.equals("pear")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pineapple);
				mp.start();
				categoryName = "pineapple";
				txHeadingItem.setText("Pineapple");
				imageView_categoryimage.setBackgroundResource(R.drawable.pineapple);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_peach);
				mp.start();
				categoryName = "peach";
				txHeadingItem.setText("Peach");
				imageView_categoryimage.setBackgroundResource(R.drawable.peach);
			}
		}else if(categoryName.equals("pineapple")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_plum);
				mp.start();
				categoryName = "plum";
				txHeadingItem.setText("Plum");
				imageView_categoryimage.setBackgroundResource(R.drawable.plum);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pear);
				mp.start();
				categoryName = "pear";
				txHeadingItem.setText("Pear");
				imageView_categoryimage.setBackgroundResource(R.drawable.pear);
			}
		}else if(categoryName.equals("plum")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pomegranate);
				mp.start();
				categoryName = "pomegranate";
				txHeadingItem.setText("Pomegranate");
				imageView_categoryimage.setBackgroundResource(R.drawable.pomegranate);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pineapple);
				mp.start();
				categoryName = "pineapple";
				txHeadingItem.setText("Pineapple");
				imageView_categoryimage.setBackgroundResource(R.drawable.pineapple);
			}
		}else if(categoryName.equals("pomegranate")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_raspberry);
				mp.start();
				categoryName = "raspberry";
				txHeadingItem.setText("Raspberry");
				imageView_categoryimage.setBackgroundResource(R.drawable.raspberry);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_plum);
				mp.start();
				categoryName = "plum";
				txHeadingItem.setText("Plum");
				imageView_categoryimage.setBackgroundResource(R.drawable.plum);
			}
		}else if(categoryName.equals("raspberry")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_strawberry);
				mp.start();
				categoryName = "strawberry";
				txHeadingItem.setText("Strawberry");
				imageView_categoryimage.setBackgroundResource(R.drawable.strawberry);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_pomegranate);
				mp.start();
				categoryName = "pomegranate";
				txHeadingItem.setText("Pomegranate");
				imageView_categoryimage.setBackgroundResource(R.drawable.pomegranate);
			}
		}else if(categoryName.equals("strawberry")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_watermelon);
				mp.start();
				categoryName = "watermelon";
				txHeadingItem.setText("Watermelon");
				imageView_categoryimage.setBackgroundResource(R.drawable.watermelon);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_raspberry);
				mp.start();
				categoryName = "raspberry";
				txHeadingItem.setText("Raspberry");
				imageView_categoryimage.setBackgroundResource(R.drawable.raspberry);
			}
		}else if(categoryName.equals("watermelon")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_apple);
				mp.start();
				categoryName = "apple";
				txHeadingItem.setText("Apple");
				imageView_categoryimage.setBackgroundResource(R.drawable.apple);
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.fruits_strawberry);
				mp.start();
				categoryName = "strawberry";
				txHeadingItem.setText("Strawberry");
				imageView_categoryimage.setBackgroundResource(R.drawable.strawberry);
			}
		}

	}

	private void setVegetables() {
		if(categoryName.equals("artichoke")){
			if(swipeType.equals("Left")){
				categoryName = "beetroot";
				txHeadingItem.setText("Beetroot");
				imageView_categoryimage.setBackgroundResource(R.drawable.beetroot);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_beetroot);
				mp.start();
			}else{
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_tomato);
				mp.start();
				categoryName = "tomato";
				txHeadingItem.setText("Tomato");
				imageView_categoryimage.setBackgroundResource(R.drawable.tomato);
			}
		}else if(categoryName.equals("beetroot")){
			if(swipeType.equals("Left")){
				categoryName = "bellpepper";
				txHeadingItem.setText("Bellpepper");
				imageView_categoryimage.setBackgroundResource(R.drawable.bellpepper);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_bellpepper);
				mp.start();
			}else{
				categoryName = "artichoke";
				txHeadingItem.setText("Artichoke");
				imageView_categoryimage.setBackgroundResource(R.drawable.artichoke);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_artichoke);
				mp.start();
			}
		}else if(categoryName.equals("bellpepper")){
			if(swipeType.equals("Left")){
				categoryName = "broccoli";
				txHeadingItem.setText("Broccoli");
				imageView_categoryimage.setBackgroundResource(R.drawable.broccoli);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_broccoli);
				mp.start();
			}else{
				categoryName = "beetroot";
				txHeadingItem.setText("Beetroot");
				imageView_categoryimage.setBackgroundResource(R.drawable.beetroot);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_beetroot);
				mp.start();
			}
		}else if(categoryName.equals("broccoli")){
			if(swipeType.equals("Left")){
				categoryName = "cabbage";
				txHeadingItem.setText("Cabbage");
				imageView_categoryimage.setBackgroundResource(R.drawable.cabbage);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cabbage);
				mp.start();
			}else{
				categoryName = "bellpepper";
				txHeadingItem.setText("Bellpepper");
				imageView_categoryimage.setBackgroundResource(R.drawable.bellpepper);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_bellpepper);
				mp.start();
			}
		}else if(categoryName.equals("cabbage")){
			if(swipeType.equals("Left")){
				categoryName = "carrot";
				txHeadingItem.setText("Carrot");
				imageView_categoryimage.setBackgroundResource(R.drawable.carrot);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_carrot);
				mp.start();
			}else{
				categoryName = "broccoli";
				txHeadingItem.setText("Broccoli");
				imageView_categoryimage.setBackgroundResource(R.drawable.broccoli);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_broccoli);
				mp.start();
			}
		}else if(categoryName.equals("carrot")){
			if(swipeType.equals("Left")){
				categoryName = "cauliflower";
				txHeadingItem.setText("Cauliflower");
				imageView_categoryimage.setBackgroundResource(R.drawable.cauliflower);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cauliflower);
				mp.start();
			}else{
				categoryName = "cabbage";
				txHeadingItem.setText("Cabbage");
				imageView_categoryimage.setBackgroundResource(R.drawable.cabbage);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cabbage);
				mp.start();
			}
		}else if(categoryName.equals("cauliflower")){
			if(swipeType.equals("Left")){
				categoryName = "corn";
				txHeadingItem.setText("Corn");
				imageView_categoryimage.setBackgroundResource(R.drawable.corn);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_corn);
				mp.start();
			}else{
				categoryName = "carrot";
				txHeadingItem.setText("Carrot");
				imageView_categoryimage.setBackgroundResource(R.drawable.carrot);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_carrot);
				mp.start();
			}
		}else if(categoryName.equals("corn")){
			if(swipeType.equals("Left")){
				categoryName = "cucumber";
				txHeadingItem.setText("Cucumber");
				imageView_categoryimage.setBackgroundResource(R.drawable.cucumber);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cucumber);
				mp.start();
			}else{
				categoryName = "cauliflower";
				txHeadingItem.setText("Cauliflower");
				imageView_categoryimage.setBackgroundResource(R.drawable.cauliflower);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cauliflower);
				mp.start();
			}
		}else if(categoryName.equals("cucumber")){
			if(swipeType.equals("Left")){
				categoryName = "eggplant";
				txHeadingItem.setText("Egg Plant");
				imageView_categoryimage.setBackgroundResource(R.drawable.eggplant);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_eggplant);
				mp.start();
			}else{
				categoryName = "corn";
				txHeadingItem.setText("Corn");
				imageView_categoryimage.setBackgroundResource(R.drawable.corn);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_corn);
				mp.start();
			}
		}else if(categoryName.equals("eggplant")){
			if(swipeType.equals("Left")){
				categoryName = "garlic";
				txHeadingItem.setText("Garlic");
				imageView_categoryimage.setBackgroundResource(R.drawable.garlic);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_garlic);
				mp.start();
			}else{
				categoryName = "cucumber";
				txHeadingItem.setText("Cucumber");
				imageView_categoryimage.setBackgroundResource(R.drawable.cucumber);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_cucumber);
				mp.start();
			}
		}else if(categoryName.equals("garlic")){
			if(swipeType.equals("Left")){
				categoryName = "greenpeas";
				txHeadingItem.setText("Green Peas");
				imageView_categoryimage.setBackgroundResource(R.drawable.greenpeas);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_greenpeas);
				mp.start();
			}else{
				categoryName = "eggplant";
				txHeadingItem.setText("Egg Plant");
				imageView_categoryimage.setBackgroundResource(R.drawable.eggplant);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_eggplant);
				mp.start();
			}
		}else if(categoryName.equals("greenpeas")){
			if(swipeType.equals("Left")){
				categoryName = "lettuce";
				txHeadingItem.setText("Lettuce");
				imageView_categoryimage.setBackgroundResource(R.drawable.lettuce);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_lettuce);
				mp.start();
			}else{
				categoryName = "garlic";
				txHeadingItem.setText("Garlic");
				imageView_categoryimage.setBackgroundResource(R.drawable.garlic);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_garlic);
				mp.start();
			}
		}else if(categoryName.equals("lettuce")){
			if(swipeType.equals("Left")){
				categoryName = "okra";
				txHeadingItem.setText("Okra");
				imageView_categoryimage.setBackgroundResource(R.drawable.okra);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_okra);
				mp.start();
			}else{
				categoryName = "greenpeas";
				txHeadingItem.setText("Green Peas");
				imageView_categoryimage.setBackgroundResource(R.drawable.greenpeas);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_greenpeas);
				mp.start();
			}
		}else if(categoryName.equals("okra")){
			if(swipeType.equals("Left")){
				categoryName = "onion";
				txHeadingItem.setText("Onion");
				imageView_categoryimage.setBackgroundResource(R.drawable.onion);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_onion);
				mp.start();
			}else{
				categoryName = "lettuce";
				txHeadingItem.setText("Lettuce");
				imageView_categoryimage.setBackgroundResource(R.drawable.lettuce);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_lettuce);
				mp.start();
			}
		}else if(categoryName.equals("onion")){
			if(swipeType.equals("Left")){
				categoryName = "parsnip";
				txHeadingItem.setText("Parsnip");
				imageView_categoryimage.setBackgroundResource(R.drawable.parsnip);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_parsnip);
				mp.start();
			}else{
				categoryName = "okra";
				txHeadingItem.setText("Okra");
				imageView_categoryimage.setBackgroundResource(R.drawable.okra);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_okra);
				mp.start();
			}
		}else if(categoryName.equals("parsnip")){
			if(swipeType.equals("Left")){
				categoryName = "potato";
				txHeadingItem.setText("Potato");
				imageView_categoryimage.setBackgroundResource(R.drawable.potato);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_potato);
				mp.start();
			}else{
				categoryName = "onion";
				txHeadingItem.setText("Onion");
				imageView_categoryimage.setBackgroundResource(R.drawable.onion);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_onion);
				mp.start();
			}
		}else if(categoryName.equals("potato")){
			if(swipeType.equals("Left")){
				categoryName = "pumpkin";
				txHeadingItem.setText("Pumpkin");
				imageView_categoryimage.setBackgroundResource(R.drawable.pumpkin);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_pumpkin);
				mp.start();
			}else{
				categoryName = "parsnip";
				txHeadingItem.setText("Parsnip");
				imageView_categoryimage.setBackgroundResource(R.drawable.parsnip);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_parsnip);
				mp.start();
			}
		}else if(categoryName.equals("pumpkin")){
			if(swipeType.equals("Left")){
				categoryName = "radish";
				txHeadingItem.setText("Radish");
				imageView_categoryimage.setBackgroundResource(R.drawable.radish);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_radish);
				mp.start();
			}else{
				categoryName = "potato";
				txHeadingItem.setText("Potato");
				imageView_categoryimage.setBackgroundResource(R.drawable.potato);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_potato);
				mp.start();
			}
		}else if(categoryName.equals("radish")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_tomato);
				mp.start();
				categoryName = "tomato";
				txHeadingItem.setText("Tomato");
				imageView_categoryimage.setBackgroundResource(R.drawable.tomato);
			}else{
				categoryName = "pumpkin";
				txHeadingItem.setText("Pumpkin");
				imageView_categoryimage.setBackgroundResource(R.drawable.pumpkin);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_pumpkin);
				mp.start();
			}
		}else if(categoryName.equals("tomato")){
			if(swipeType.equals("Left")){
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_artichoke);
				mp.start();
				categoryName = "artichoke";
				txHeadingItem.setText("Artichoke");
				imageView_categoryimage.setBackgroundResource(R.drawable.artichoke);

			}else{
				categoryName = "radish";
				txHeadingItem.setText("Radish");
				imageView_categoryimage.setBackgroundResource(R.drawable.radish);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.vegetables_radish);
				mp.start();
			}	
		}
	}

	private void setShapes(){
		if(categoryName.equals("square")){
			if(swipeType.equals("Left")){
				categoryName = "star";
				txHeadingItem.setText("Star");
				imageView_categoryimage.setBackgroundResource(R.drawable.star);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_star);
				mp.start();
			}else{
				categoryName = "rectangle";
				txHeadingItem.setText("Rectangle");
				imageView_categoryimage.setBackgroundResource(R.drawable.rectangle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_rectangle);
				mp.start();
			}
		}else if(categoryName.equals("star")){
			if(swipeType.equals("Left")){
				categoryName = "triangle";
				txHeadingItem.setText("Triangle");
				imageView_categoryimage.setBackgroundResource(R.drawable.triangle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_triangle);
				mp.start();
			}else{
				categoryName = "square";
				txHeadingItem.setText("Square");
				imageView_categoryimage.setBackgroundResource(R.drawable.square);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_square);
				mp.start();
			}
		}else if(categoryName.equals("triangle")){
			if(swipeType.equals("Left")){
				categoryName = "arrow";
				txHeadingItem.setText("Arrow");
				imageView_categoryimage.setBackgroundResource(R.drawable.arrow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_arrow);
				mp.start();
			}else{
				categoryName = "star";
				txHeadingItem.setText("Star");
				imageView_categoryimage.setBackgroundResource(R.drawable.star);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_star);
				mp.start();
			}
		}else if(categoryName.equals("arrow")){
			if(swipeType.equals("Left")){
				categoryName = "circle";
				txHeadingItem.setText("Circle");
				imageView_categoryimage.setBackgroundResource(R.drawable.circle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_circle);
				mp.start();
			}else{
				categoryName = "triangle";
				txHeadingItem.setText("Triangle");
				imageView_categoryimage.setBackgroundResource(R.drawable.triangle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_triangle);
				mp.start();
			}
		}else if(categoryName.equals("circle")){
			if(swipeType.equals("Left")){
				categoryName = "crescent";
				txHeadingItem.setText("Crescent");
				imageView_categoryimage.setBackgroundResource(R.drawable.crescent);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_crescent);
				mp.start();
			}else{
				categoryName = "arrow";
				txHeadingItem.setText("Arrow");
				imageView_categoryimage.setBackgroundResource(R.drawable.arrow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_arrow);
				mp.start();
			}
		}else if(categoryName.equals("crescent")){
			if(swipeType.equals("Left")){
				categoryName = "cross";
				txHeadingItem.setText("Cross");
				imageView_categoryimage.setBackgroundResource(R.drawable.cross);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_cross);
				mp.start();
			}else{
				categoryName = "circle";
				txHeadingItem.setText("Circle");
				imageView_categoryimage.setBackgroundResource(R.drawable.circle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_circle);
				mp.start();
			}
		}else if(categoryName.equals("cross")){
			if(swipeType.equals("Left")){
				categoryName = "diamond";
				txHeadingItem.setText("Diamond");
				imageView_categoryimage.setBackgroundResource(R.drawable.diamond);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_diamond);
				mp.start();
			}else{
				categoryName = "crescent";
				txHeadingItem.setText("Crescent");
				imageView_categoryimage.setBackgroundResource(R.drawable.crescent);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_crescent);
				mp.start();
			}
		}else if(categoryName.equals("diamond")){
			if(swipeType.equals("Left")){
				categoryName = "heart";
				txHeadingItem.setText("Heart");
				imageView_categoryimage.setBackgroundResource(R.drawable.heart);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_heart);
				mp.start();
			}else{
				categoryName = "cross";
				txHeadingItem.setText("Cross");
				imageView_categoryimage.setBackgroundResource(R.drawable.cross);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_cross);
				mp.start();
			}
		}else if(categoryName.equals("heart")){
			if(swipeType.equals("Left")){
				categoryName = "heaxagon";
				txHeadingItem.setText("Heaxagon");
				imageView_categoryimage.setBackgroundResource(R.drawable.hexagon);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_hexagon);
				mp.start();
			}else{
				categoryName = "diamond";
				txHeadingItem.setText("Diamond");
				imageView_categoryimage.setBackgroundResource(R.drawable.diamond);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_diamond);
				mp.start();
			}
		}else if(categoryName.equals("heaxagon")){
			if(swipeType.equals("Left")){
				categoryName = "oval";
				txHeadingItem.setText("Oval");
				imageView_categoryimage.setBackgroundResource(R.drawable.oval);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_oval);
				mp.start();
			}else{
				categoryName = "heart";
				txHeadingItem.setText("Heart");
				imageView_categoryimage.setBackgroundResource(R.drawable.heart);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_heart);
				mp.start();
			}
		}else if(categoryName.equals("oval")){
			if(swipeType.equals("Left")){
				categoryName = "pentagon";
				txHeadingItem.setText("Pentagon");
				imageView_categoryimage.setBackgroundResource(R.drawable.pentagon);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_pentagon);
				mp.start();
			}else{
				categoryName = "heaxagon";
				txHeadingItem.setText("Heaxagon");
				imageView_categoryimage.setBackgroundResource(R.drawable.hexagon);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_hexagon);
				mp.start();
			}
		}else if(categoryName.equals("pentagon")){

			if(swipeType.equals("Left")){
				categoryName = "rectangle";
				txHeadingItem.setText("Rectangle");
				imageView_categoryimage.setBackgroundResource(R.drawable.rectangle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_rectangle);
				mp.start();
			}else{
				categoryName = "oval";
				txHeadingItem.setText("Oval");
				imageView_categoryimage.setBackgroundResource(R.drawable.oval);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_oval);
				mp.start();
			}
		}else if(categoryName.equals("rectangle")){

			if(swipeType.equals("Left")){
				categoryName = "square";
				txHeadingItem.setText("Square");
				imageView_categoryimage.setBackgroundResource(R.drawable.square);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_square);
				mp.start();
			}else{
				categoryName = "pentagon";
				txHeadingItem.setText("Pentagon");
				imageView_categoryimage.setBackgroundResource(R.drawable.pentagon);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.shapes_pentagon);
				mp.start();
			}	
		}
	}




	private void setColor(){
		if(categoryName.equals("darkgreen")){
			if(swipeType.equals("Left")){
				categoryName = "darkorange";
				txHeadingItem.setText("Dark Orange");
				imageView_categoryimage.setBackgroundResource(R.drawable.darkorange);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_darkorange);
				mp.start();
			}else{
				categoryName = "brown";
				txHeadingItem.setText("Brown");
				imageView_categoryimage.setBackgroundResource(R.drawable.brown);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_brown);
				mp.start();
			}
		}else if(categoryName.equals("darkorange")){
			if(swipeType.equals("Left")){
				categoryName = "green";
				txHeadingItem.setText("Green");
				imageView_categoryimage.setBackgroundResource(R.drawable.green);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_green);
				mp.start();
			}else{
				categoryName = "darkgreen";
				txHeadingItem.setText("Dark Green");
				imageView_categoryimage.setBackgroundResource(R.drawable.darkgreen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_darkgreen);
				mp.start();
			}
		}else if(categoryName.equals("green")){
			if(swipeType.equals("Left")){
				categoryName = "grey";
				txHeadingItem.setText("Grey");
				imageView_categoryimage.setBackgroundResource(R.drawable.grey);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_grey);
				mp.start();
			}else{
				categoryName = "darkorange";
				txHeadingItem.setText("Dark Orange");
				imageView_categoryimage.setBackgroundResource(R.drawable.darkorange);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_darkorange);
				mp.start();
			}
		}else if(categoryName.equals("grey")){
			if(swipeType.equals("Left")){
				categoryName = "indigo";
				txHeadingItem.setText("Indigo");
				imageView_categoryimage.setBackgroundResource(R.drawable.indigo);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_indigo);
				mp.start();
			}else{
				categoryName = "green";
				txHeadingItem.setText("Green");
				imageView_categoryimage.setBackgroundResource(R.drawable.green);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_green);
				mp.start();
			}
		}else if(categoryName.equals("indigo")){
			if(swipeType.equals("Left")){
				categoryName = "lavender";
				txHeadingItem.setText("Lavender");
				imageView_categoryimage.setBackgroundResource(R.drawable.lavender);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_lavender);
				mp.start();
			}else{
				categoryName = "grey";
				txHeadingItem.setText("Grey");
				imageView_categoryimage.setBackgroundResource(R.drawable.grey);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_grey);
				mp.start();
			}			
		}else if(categoryName.equals("lavender")){
			if(swipeType.equals("Left")){
				categoryName = "navyblue";
				txHeadingItem.setText("Navy-Blue");
				imageView_categoryimage.setBackgroundResource(R.drawable.navyblue);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_navyblue);
				mp.start();
			}else{
				categoryName = "indigo";
				txHeadingItem.setText("Indigo");
				imageView_categoryimage.setBackgroundResource(R.drawable.indigo);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_indigo);
				mp.start();
			}
		}else if(categoryName.equals("navyblue")){
			if(swipeType.equals("Left")){
				categoryName = "pink";
				txHeadingItem.setText("Pink");
				imageView_categoryimage.setBackgroundResource(R.drawable.pink);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_pink);
				mp.start();
			}else{
				categoryName = "lavender";
				txHeadingItem.setText("Lavender");
				imageView_categoryimage.setBackgroundResource(R.drawable.lavender);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_lavender);
				mp.start();
			}
		}else if(categoryName.equals("pink")){
			if(swipeType.equals("Left")){
				categoryName = "red";
				txHeadingItem.setText("Red");
				imageView_categoryimage.setBackgroundResource(R.drawable.red);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_red);
				mp.start();
			}else{
				categoryName = "navyblue";
				txHeadingItem.setText("Navy-Blue");
				imageView_categoryimage.setBackgroundResource(R.drawable.navyblue);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_navyblue);
				mp.start();
			}
		}else if(categoryName.equals("red")){
			if(swipeType.equals("Left")){
				categoryName = "white";
				txHeadingItem.setText("White");
				imageView_categoryimage.setBackgroundResource(R.drawable.white);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_white);
				mp.start();
			}else{
				categoryName = "pink";
				txHeadingItem.setText("Pink");
				imageView_categoryimage.setBackgroundResource(R.drawable.pink);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_pink);
				mp.start();
			}
		}else if(categoryName.equals("white")){
			if(swipeType.equals("Left")){
				categoryName = "yellow";
				txHeadingItem.setText("Yellow");
				imageView_categoryimage.setBackgroundResource(R.drawable.yellow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_yellow);
				mp.start();
			}else{
				categoryName = "red";
				txHeadingItem.setText("Red");
				imageView_categoryimage.setBackgroundResource(R.drawable.red);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_red);
				mp.start();
			}
		}else if(categoryName.equals("yellow")){
			if(swipeType.equals("Left")){
				categoryName = "beige";
				txHeadingItem.setText("Beige");
				imageView_categoryimage.setBackgroundResource(R.drawable.beige);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_beige);
				mp.start();
			}else{
				categoryName = "white";
				txHeadingItem.setText("White");
				imageView_categoryimage.setBackgroundResource(R.drawable.white);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_white);
				mp.start();
			}
		}else if(categoryName.equals("beige")){
			if(swipeType.equals("Left")){
				categoryName = "black";
				txHeadingItem.setText("Black");
				imageView_categoryimage.setBackgroundResource(R.drawable.black);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_black);
				mp.start();
			}else{
				categoryName = "yellow";
				txHeadingItem.setText("Yellow");
				imageView_categoryimage.setBackgroundResource(R.drawable.yellow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_yellow);
				mp.start();
			}
		}else if(categoryName.equals("black")){
			if(swipeType.equals("Left")){
				categoryName = "blue";
				txHeadingItem.setText("Blue");
				imageView_categoryimage.setBackgroundResource(R.drawable.blue);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_blue);
				mp.start();
			}else{
				categoryName = "beige";
				txHeadingItem.setText("Beige");
				imageView_categoryimage.setBackgroundResource(R.drawable.beige);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_beige);
				mp.start();
			}
		}else if(categoryName.equals("blue")){
			if(swipeType.equals("Left")){
				categoryName = "brown";
				txHeadingItem.setText("Brown");
				imageView_categoryimage.setBackgroundResource(R.drawable.brown);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_brown);
				mp.start();
			}else{
				categoryName = "black";
				txHeadingItem.setText("Black");
				imageView_categoryimage.setBackgroundResource(R.drawable.black);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_black);
				mp.start();
			}
		}else if(categoryName.equals("brown")){
			if(swipeType.equals("Left")){
				categoryName = "darkgreen";
				txHeadingItem.setText("Dark Green");
				imageView_categoryimage.setBackgroundResource(R.drawable.darkgreen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_darkgreen);
				mp.start();
			}else{
				categoryName = "blue";
				txHeadingItem.setText("Blue");
				imageView_categoryimage.setBackgroundResource(R.drawable.blue);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.colors_blue);
				mp.start();
			}	
		}
	}

	private void callNumber(){
		if(categoryName.equals("one")){
			if(swipeType.equals("Left")){
				categoryName = "two";
				txHeadingItem.setText("Two");
				imageView_categoryimage.setBackgroundResource(R.drawable.two);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_two);
				mp.start();
			}else{
				categoryName = "twenty";
				txHeadingItem.setText("Twenty");
				imageView_categoryimage.setBackgroundResource(R.drawable.twenty);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_twenty);
				mp.start();
			}
		}else if(categoryName.equals("two")){
			if(swipeType.equals("Left")){
				categoryName = "three";
				txHeadingItem.setText("Three");
				imageView_categoryimage.setBackgroundResource(R.drawable.three);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_three);
				mp.start();
			}else{
				categoryName = "one";
				txHeadingItem.setText("One");
				imageView_categoryimage.setBackgroundResource(R.drawable.one);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_one);
				mp.start();
			}
		}else if(categoryName.equals("three")){
			if(swipeType.equals("Left")){
				categoryName = "four";
				txHeadingItem.setText("Four");
				imageView_categoryimage.setBackgroundResource(R.drawable.four);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_four);
				mp.start();
			}else{
				categoryName = "two";
				txHeadingItem.setText("Two");
				imageView_categoryimage.setBackgroundResource(R.drawable.two);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_two);
				mp.start();
			}
		}else if(categoryName.equals("four")){
			if(swipeType.equals("Left")){
				categoryName = "five";
				txHeadingItem.setText("Five");
				imageView_categoryimage.setBackgroundResource(R.drawable.five);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_five);
				mp.start();
			}else{
				categoryName = "three";
				txHeadingItem.setText("Three");
				imageView_categoryimage.setBackgroundResource(R.drawable.three);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_three);
				mp.start();
			}
		}else if(categoryName.equals("five")){
			if(swipeType.equals("Left")){
				categoryName = "six";
				txHeadingItem.setText("Six");
				imageView_categoryimage.setBackgroundResource(R.drawable.six);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_six);
				mp.start();
			}else{
				categoryName = "four";
				txHeadingItem.setText("Four");
				imageView_categoryimage.setBackgroundResource(R.drawable.four);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_four);
				mp.start();
			}
		}else if(categoryName.equals("six")){
			if(swipeType.equals("Left")){
				categoryName = "seven";
				txHeadingItem.setText("Seven");
				imageView_categoryimage.setBackgroundResource(R.drawable.seven);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_seven);
				mp.start();
			}else{
				categoryName = "five";
				txHeadingItem.setText("Five");
				imageView_categoryimage.setBackgroundResource(R.drawable.five);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_five);
				mp.start();
			}
		}else if(categoryName.equals("seven")){
			if(swipeType.equals("Left")){
				categoryName = "eight";
				txHeadingItem.setText("Eight");
				imageView_categoryimage.setBackgroundResource(R.drawable.eight);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eight);
				mp.start();
			}else{
				categoryName = "six";
				txHeadingItem.setText("Six");
				imageView_categoryimage.setBackgroundResource(R.drawable.six);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_six);
				mp.start();
			}
		}else if(categoryName.equals("eight")){
			if(swipeType.equals("Left")){
				categoryName = "nine";
				txHeadingItem.setText("Nine");
				imageView_categoryimage.setBackgroundResource(R.drawable.nine);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_nine);
				mp.start();
			}else{
				categoryName = "seven";
				txHeadingItem.setText("Seven");
				imageView_categoryimage.setBackgroundResource(R.drawable.seven);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_seven);
				mp.start();
			}
		}else if(categoryName.equals("nine")){
			if(swipeType.equals("Left")){
				categoryName = "ten";
				txHeadingItem.setText("Ten");
				imageView_categoryimage.setBackgroundResource(R.drawable.ten);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_ten);
				mp.start();
			}else{
				categoryName = "eight";
				txHeadingItem.setText("Eight");
				imageView_categoryimage.setBackgroundResource(R.drawable.eight);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eight);
				mp.start();
			}
		}else if(categoryName.equals("ten")){
			if(swipeType.equals("Left")){
				categoryName = "eleven";
				txHeadingItem.setText("Eleven");
				imageView_categoryimage.setBackgroundResource(R.drawable.eleven);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eleven);
				mp.start();
			}else{
				categoryName = "nine";
				txHeadingItem.setText("Nine");
				imageView_categoryimage.setBackgroundResource(R.drawable.nine);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_nine);
				mp.start();
			}
		}else if(categoryName.equals("eleven")){
			if(swipeType.equals("Left")){
				categoryName = "twelve";
				txHeadingItem.setText("Twelve");
				imageView_categoryimage.setBackgroundResource(R.drawable.twelve);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_twelve);
				mp.start();
			}else{
				categoryName = "ten";
				txHeadingItem.setText("Ten");
				imageView_categoryimage.setBackgroundResource(R.drawable.ten);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_ten);
				mp.start();
			}
		}else if(categoryName.equals("twelve")){
			if(swipeType.equals("Left")){
				categoryName = "thirteen";
				txHeadingItem.setText("Thirteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.thirteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_thirteen);
				mp.start();
			}else{
				categoryName = "eleven";
				txHeadingItem.setText("Eleven");
				imageView_categoryimage.setBackgroundResource(R.drawable.eleven);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eleven);
				mp.start();
			}
		}else if(categoryName.equals("thirteen")){
			if(swipeType.equals("Left")){
				categoryName = "fourteen";
				txHeadingItem.setText("Fourteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.fourteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_fourteen);
				mp.start();
			}else{
				categoryName = "twelve";
				txHeadingItem.setText("Twelve");
				imageView_categoryimage.setBackgroundResource(R.drawable.twelve);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_twelve);
				mp.start();
			}
		}else if(categoryName.equals("fourteen")){
			if(swipeType.equals("Left")){
				categoryName = "fifteen";
				txHeadingItem.setText("Fifteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.fifteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_fifteen);
				mp.start();
			}else{
				categoryName = "thirteen";
				txHeadingItem.setText("Thirteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.thirteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_thirteen);
				mp.start();
			}
		}else if(categoryName.equals("fifteen")){
			if(swipeType.equals("Left")){
				categoryName = "sixteen";
				txHeadingItem.setText("Sixteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.sixteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_sixteen);
				mp.start();
			}else{
				categoryName = "fourteen";
				txHeadingItem.setText("Fourteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.fourteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_fourteen);
				mp.start();
			}
		}else if(categoryName.equals("sixteen")){
			if(swipeType.equals("Left")){
				categoryName = "seventeen";
				txHeadingItem.setText("Seventeen");
				imageView_categoryimage.setBackgroundResource(R.drawable.seventeen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_seventeen);
				mp.start();
			}else{
				categoryName = "fifteen";
				txHeadingItem.setText("Fifteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.fifteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_fifteen);
				mp.start();
			}
		}else if(categoryName.equals("seventeen")){
			if(swipeType.equals("Left")){
				categoryName = "eighteen";
				txHeadingItem.setText("Eighteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.eighteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eighteen);
				mp.start();
			}else{
				categoryName = "sixteen";
				txHeadingItem.setText("Sixteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.sixteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_sixteen);
				mp.start();
			}
		}else if(categoryName.equals("eighteen")){
			if(swipeType.equals("Left")){
				categoryName = "ninteen";
				txHeadingItem.setText("Ninteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.nineteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_nineteen);
				mp.start();
			}else{
				categoryName = "seventeen";
				txHeadingItem.setText("Seventeen");
				imageView_categoryimage.setBackgroundResource(R.drawable.seventeen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_seventeen);
				mp.start();
			}
		}else if(categoryName.equals("ninteen")){
			if(swipeType.equals("Left")){
				categoryName = "twenty";
				txHeadingItem.setText("Twenty");
				imageView_categoryimage.setBackgroundResource(R.drawable.twenty);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_twenty);
				mp.start();
			}else{
				categoryName = "eighteen";
				txHeadingItem.setText("Eighteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.eighteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_eighteen);
				mp.start();
			}
		}else if(categoryName.equals("twenty")){
			if(swipeType.equals("Left")){
				categoryName = "one";
				txHeadingItem.setText("One");
				imageView_categoryimage.setBackgroundResource(R.drawable.one);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_one);
				mp.start();
			}else{
				categoryName = "ninteen";
				txHeadingItem.setText("Ninteen");
				imageView_categoryimage.setBackgroundResource(R.drawable.nineteen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.numbers_nineteen);
				mp.start();
			}	
		}
	}

	private void callAlphabet() {
		if(categoryName.equals("apple")){
			if(swipeType.equals("Left")){
				categoryName = "ball";
				txHeadingItem.setText("Ball");
				imageView_categoryimage.setBackgroundResource(R.drawable.ball);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_ball);
				mp.start();
			}else{
				categoryName = "zipper";
				txHeadingItem.setText("Zipper");
				imageView_categoryimage.setBackgroundResource(R.drawable.zipper);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_zipper);
				mp.start();
			}
		}else if(categoryName.equals("ball")){
			if(swipeType.equals("Left")){
				categoryName = "cake";
				txHeadingItem.setText("Cake");
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_cake);
				mp.start();
				imageView_categoryimage.setBackgroundResource(R.drawable.cake);
			}else{
				categoryName = "apple";
				txHeadingItem.setText("Apple");
				imageView_categoryimage.setBackgroundResource(R.drawable.apple);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_apple);
				mp.start();
			}
		}else if(categoryName.equals("cake")){
			if(swipeType.equals("Left")){
				categoryName = "doll";
				txHeadingItem.setText("Doll");
				imageView_categoryimage.setBackgroundResource(R.drawable.doll);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_doll);
				mp.start();
			}else{
				categoryName = "ball";
				txHeadingItem.setText("Ball");
				imageView_categoryimage.setBackgroundResource(R.drawable.ball);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_ball);
				mp.start();
			}
		}else if(categoryName.equals("doll")){
			if(swipeType.equals("Left")){
				categoryName = "eggs";
				txHeadingItem.setText("Egg");
				imageView_categoryimage.setBackgroundResource(R.drawable.eggs);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_eggs);
				mp.start();
			}else{
				categoryName = "cake";
				txHeadingItem.setText("Cake");
				imageView_categoryimage.setBackgroundResource(R.drawable.cake);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_cake);
				mp.start();
			}
		}else if(categoryName.equals("eggs")){
			if(swipeType.equals("Left")){
				categoryName = "fish";
				txHeadingItem.setText("Fish");
				imageView_categoryimage.setBackgroundResource(R.drawable.fish);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_fish);
				mp.start();
			}else{
				categoryName = "doll";
				txHeadingItem.setText("Doll");
				imageView_categoryimage.setBackgroundResource(R.drawable.doll);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_doll);
				mp.start();
			}
		}else if(categoryName.equals("fish")){
			if(swipeType.equals("Left")){
				categoryName = "grapes";
				txHeadingItem.setText("Grapes");
				imageView_categoryimage.setBackgroundResource(R.drawable.grapes);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_grapes);
				mp.start();
			}else{
				categoryName = "eggs";
				txHeadingItem.setText("Egg");
				imageView_categoryimage.setBackgroundResource(R.drawable.eggs);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_eggs);
				mp.start();
			}

		}else if(categoryName.equals("grapes")){
			if(swipeType.equals("Left")){
				categoryName = "hat";
				txHeadingItem.setText("Hat");
				imageView_categoryimage.setBackgroundResource(R.drawable.hat);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_hat);
				mp.start();
			}else{
				categoryName = "fish";
				txHeadingItem.setText("Fish");
				imageView_categoryimage.setBackgroundResource(R.drawable.fish);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_fish);
				mp.start();
			}
		}else if(categoryName.equals("hat")){
			if(swipeType.equals("Left")){
				categoryName = "icecream";
				txHeadingItem.setText("Ice-Cream");
				imageView_categoryimage.setBackgroundResource(R.drawable.icecream);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_icecream);
				mp.start();
			}else{
				categoryName = "grapes";
				txHeadingItem.setText("Grapes");
				imageView_categoryimage.setBackgroundResource(R.drawable.grapes);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_grapes);
				mp.start();
			}
		}else if(categoryName.equals("icecream")){
			if(swipeType.equals("Left")){
				categoryName = "juice";
				txHeadingItem.setText("Juice");
				imageView_categoryimage.setBackgroundResource(R.drawable.juice);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_juice);
				mp.start();
			}else{
				categoryName = "hat";
				txHeadingItem.setText("Hat");
				imageView_categoryimage.setBackgroundResource(R.drawable.hat);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_hat);
				mp.start();
			}
		}else if(categoryName.equals("juice")){
			if(swipeType.equals("Left")){
				categoryName = "kettle";
				txHeadingItem.setText("Kettle");
				imageView_categoryimage.setBackgroundResource(R.drawable.kettle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_kettle);
				mp.start();
			}else{
				categoryName = "icecream";
				txHeadingItem.setText("Ice-Cream");
				imageView_categoryimage.setBackgroundResource(R.drawable.icecream);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_icecream);
				mp.start();
			}
		}else if(categoryName.equals("kettle")){
			if(swipeType.equals("Left")){
				categoryName = "leaf";
				txHeadingItem.setText("Leaf");
				imageView_categoryimage.setBackgroundResource(R.drawable.leaf);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_leaf);
				mp.start();
			}else{
				categoryName = "juice";
				txHeadingItem.setText("Juice");
				imageView_categoryimage.setBackgroundResource(R.drawable.juice);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_juice);
				mp.start();
			}
		}else if(categoryName.equals("leaf")){
			if(swipeType.equals("Left")){
				categoryName = "milk";
				txHeadingItem.setText("Milk");
				imageView_categoryimage.setBackgroundResource(R.drawable.milk);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_milk);
				mp.start();
			}else{
				categoryName = "kettle";
				txHeadingItem.setText("Kettle");
				imageView_categoryimage.setBackgroundResource(R.drawable.kettle);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_kettle);
				mp.start();
			}
		}else if(categoryName.equals("milk")){
			if(swipeType.equals("Left")){
				categoryName = "nest";
				txHeadingItem.setText("Nest");
				imageView_categoryimage.setBackgroundResource(R.drawable.nest);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_nest);
				mp.start();
			}else{
				categoryName = "leaf";
				txHeadingItem.setText("Leaf");
				imageView_categoryimage.setBackgroundResource(R.drawable.leaf);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_leaf);
				mp.start();
			}
		}else if(categoryName.equals("nest")){
			if(swipeType.equals("Left")){
				categoryName = "octopus";
				txHeadingItem.setText("Octopus");
				imageView_categoryimage.setBackgroundResource(R.drawable.octopus);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_octopus);
				mp.start();
			}else{
				categoryName = "milk";
				txHeadingItem.setText("Milk");
				imageView_categoryimage.setBackgroundResource(R.drawable.milk);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_milk);
				mp.start();
			}
		}else if(categoryName.equals("octopus")){
			if(swipeType.equals("Left")){
				categoryName = "pancakes";
				txHeadingItem.setText("Pan Cakes");
				imageView_categoryimage.setBackgroundResource(R.drawable.pancakes);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_pancakes);
				mp.start();
			}else{
				categoryName = "nest";
				txHeadingItem.setText("Nest");
				imageView_categoryimage.setBackgroundResource(R.drawable.nest);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_nest);
				mp.start();
			}
		}else if(categoryName.equals("pancakes")){
			if(swipeType.equals("Left")){
				categoryName = "queen";
				txHeadingItem.setText("Queen");
				imageView_categoryimage.setBackgroundResource(R.drawable.queen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_queen);
				mp.start();
			}else{
				categoryName = "octopus";
				txHeadingItem.setText("Octopus");
				imageView_categoryimage.setBackgroundResource(R.drawable.octopus);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_octopus);
				mp.start();
			}
		}else if(categoryName.equals("queen")){
			if(swipeType.equals("Left")){
				categoryName = "rainbow";
				txHeadingItem.setText("Rainbow");
				imageView_categoryimage.setBackgroundResource(R.drawable.rainbow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_rainbow);
				mp.start();
			}else{
				categoryName = "pancakes";
				txHeadingItem.setText("Pan Cakes");
				imageView_categoryimage.setBackgroundResource(R.drawable.pancakes);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_pancakes);
				mp.start();
			}
		}else if(categoryName.equals("rainbow")){
			if(swipeType.equals("Left")){
				categoryName = "sandwich";
				txHeadingItem.setText("Sandwich");
				imageView_categoryimage.setBackgroundResource(R.drawable.sandwich);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_sandwich);
				mp.start();
			}else{
				categoryName = "queen";
				txHeadingItem.setText("Queen");
				imageView_categoryimage.setBackgroundResource(R.drawable.queen);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_queen);
				mp.start();
			}
		}else if(categoryName.equals("sandwich")){
			if(swipeType.equals("Left")){
				categoryName = "teddybear";
				txHeadingItem.setText("Teddy Bear");
				imageView_categoryimage.setBackgroundResource(R.drawable.teddybear);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_teddybear);
				mp.start();
			}else{
				categoryName = "rainbow";
				txHeadingItem.setText("Rainbow");
				imageView_categoryimage.setBackgroundResource(R.drawable.rainbow);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_rainbow);
				mp.start();
			}
		}else if(categoryName.equals("teddybear")){
			if(swipeType.equals("Left")){
				categoryName = "umbrella";
				txHeadingItem.setText("Umbrella");
				imageView_categoryimage.setBackgroundResource(R.drawable.umbrella);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_umbrella);
				mp.start();
			}else{
				categoryName = "sandwich";
				txHeadingItem.setText("Sandwich");
				imageView_categoryimage.setBackgroundResource(R.drawable.sandwich);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_sandwich);
				mp.start();
			}
		}else if(categoryName.equals("umbrella")){
			if(swipeType.equals("Left")){
				categoryName = "violin";
				txHeadingItem.setText("Violin");
				imageView_categoryimage.setBackgroundResource(R.drawable.violin);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_violin);
				mp.start();
			}else{
				categoryName = "teddybear";
				txHeadingItem.setText("Teddy Bear");
				imageView_categoryimage.setBackgroundResource(R.drawable.teddybear);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_teddybear);
				mp.start();
			}
		}else if(categoryName.equals("violin")){
			if(swipeType.equals("Left")){
				categoryName = "watch";
				txHeadingItem.setText("Watch");
				imageView_categoryimage.setBackgroundResource(R.drawable.watch);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_watch);
				mp.start();
			}else{
				categoryName = "umbrella";
				txHeadingItem.setText("Umbrella");
				imageView_categoryimage.setBackgroundResource(R.drawable.umbrella);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_umbrella);
				mp.start();
			}
		}else if(categoryName.equals("watch")){
			if(swipeType.equals("Left")){
				categoryName = "xylophone";
				txHeadingItem.setText("Xylophone");
				imageView_categoryimage.setBackgroundResource(R.drawable.xylophone);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_xylophone);
				mp.start();
			}else{
				categoryName = "violin";
				txHeadingItem.setText("Violin");
				imageView_categoryimage.setBackgroundResource(R.drawable.violin);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_violin);
				mp.start();
			}
		}else if(categoryName.equals("xylophone")){
			if(swipeType.equals("Left")){
				categoryName = "yatch";
				txHeadingItem.setText("Yatch");
				imageView_categoryimage.setBackgroundResource(R.drawable.yatch);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_yatch);
				mp.start();
			}else{
				categoryName = "watch";
				txHeadingItem.setText("Watch");
				imageView_categoryimage.setBackgroundResource(R.drawable.watch);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_watch);
				mp.start();
			}
		}else if(categoryName.equals("yatch")){
			if(swipeType.equals("Left")){
				categoryName = "zipper";
				txHeadingItem.setText("Zipper");
				imageView_categoryimage.setBackgroundResource(R.drawable.zipper);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_zipper);
				mp.start();
			}else{
				categoryName = "xylophone";
				txHeadingItem.setText("Xylophone");
				imageView_categoryimage.setBackgroundResource(R.drawable.xylophone);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_xylophone);
				mp.start();
			}
		}else if(categoryName.equals("zipper")){
			if(swipeType.equals("Left")){
				categoryName = "apple";
				txHeadingItem.setText("Apple");
				imageView_categoryimage.setBackgroundResource(R.drawable.apple);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_apple);
				mp.start();
			}else{
				categoryName = "yatch";
				txHeadingItem.setText("Yatch");
				imageView_categoryimage.setBackgroundResource(R.drawable.yatch);
				mp.release();
				mp = MediaPlayer.create(this, R.raw.alphabets_yatch);
				mp.start();
			}	
		}

	}

}

