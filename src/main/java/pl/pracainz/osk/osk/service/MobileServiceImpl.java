package pl.pracainz.osk.osk.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.dao.GpsPointRepository;
import pl.pracainz.osk.osk.json.LocationJson;
@Service
public class MobileServiceImpl implements MobileService{
 
	@Autowired
	DrivingRepository drivingRepository;
	
	@Autowired
	GpsPointRepository gpsRepository;

	@Override
	public void saveLocationList(ArrayList<LocationJson> coordinates, Integer id) {

	
	
}		
	
	
	
	
	
	
	
	
	
	
	
	
}
