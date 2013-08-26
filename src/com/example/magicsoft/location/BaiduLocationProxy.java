package com.example.magicsoft.location;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.magicsoft.R;

public class BaiduLocationProxy implements BDLocationListener{

	private LocationClient 				mLocClient;
	private	MCLocationListener 			mcListener;

	public BaiduLocationProxy(Context context){
		mLocClient = new LocationClient(context);
		LocationClientOption option = new LocationClientOption();
		option.setProdName(context.getString(R.string.app_name));
		option.setCoorType("gcj02");
		option.setOpenGps(true);
		option.disableCache(true);
		option.setAddrType("all");
		mLocClient.setLocOption(option);
	}

	public void startListening(MCLocationListener mcListener){
		this.mcListener = mcListener;
		if(mLocClient!= null && !mLocClient.isStarted()){
			mLocClient.registerLocationListener(this);
			mLocClient.start();
		}
		mLocClient.requestLocation();
	}

	public void stopListening(){
		mLocClient.unRegisterLocationListener(this);
		mLocClient.stop();
		this.mcListener = null;	
	}

	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null || mcListener == null) return ;
		int errorCode = location.getLocType();
		if(errorCode >= 162){
			mcListener.enableMyLocation();
		}else{
			mcListener.onLocationChanged(location);
			mcListener.disableMyLocation();
		}
		
	}

	@Override
	public void onReceivePoi(BDLocation localtion) {
		return;
	}

}
