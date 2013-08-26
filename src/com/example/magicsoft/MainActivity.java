package com.example.magicsoft;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.example.magicsoft.location.BaiduLocationProxy;
import com.example.magicsoft.location.MCLocationListener;

public class MainActivity extends Activity implements MCLocationListener, OnClickListener{

	private 	BaiduLocationProxy 			bdLocProxy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bdLocProxy = new BaiduLocationProxy(this);
		Button btn = (Button)findViewById(R.id.location_btn);
		btn.setText("定位");
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void enableMyLocation() {
		bdLocProxy.startListening(this);
		showShortToast(this, "启动定位!!!");

	}

	@Override
	public void disableMyLocation() {
		bdLocProxy.stopListening();
		showShortToast(this, "关闭定位!!!");

	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		disableMyLocation();
	}

	@Override
	public void onLocationChanged(BDLocation location) {
		TextView textview = (TextView)findViewById(R.id.location_textview);
		Log.e("onLocationChanged", location.getLatitude()+"");
		Log.e("onLocationChanged", location.getLongitude()+"");
		textview.setText(String.format("lat:%s\tlng:%s", location.getLatitude(), 
				location.getLongitude()));

	}

	private void showShortToast(Context context, String showString) {

		Toast.makeText(context, showString, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onClick(View v) {
		enableMyLocation();
	}

}
