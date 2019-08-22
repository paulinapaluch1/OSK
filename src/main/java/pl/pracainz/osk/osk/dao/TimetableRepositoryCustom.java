package pl.pracainz.osk.osk.dao;

import java.util.Date;
import java.util.List;

import pl.pracainz.osk.osk.entity.Timetable;

public interface TimetableRepositoryCustom {

	List<Timetable> getTimetablesByDate(Date date);

}
