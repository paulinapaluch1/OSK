package pl.pracainz.osk.osk.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import pl.pracainz.osk.osk.json.LocationJson;

@Service
public interface MobileService {

	void saveLocationList(ArrayList<LocationJson> coordinates, Integer id);
}
