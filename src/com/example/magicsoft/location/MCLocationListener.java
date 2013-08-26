package com.example.magicsoft.location;

import com.baidu.location.BDLocation;

public interface MCLocationListener {
	
	public void enableMyLocation();
	
	public void disableMyLocation();
	
	public void onLocationChanged(BDLocation location);

}
