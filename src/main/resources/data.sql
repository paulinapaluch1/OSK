SELECT i.name, i.surname FROM instructors i 
JOIN timetable t ON t.id_instructor = i.id_instructor
JOIN drivings d ON d.id_driving = t.id_driving
JOIN students s ON s.id_student = d.id_student 
WHERE s.id_student = 2