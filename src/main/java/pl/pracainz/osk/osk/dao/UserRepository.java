package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pracainz.osk.osk.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
	
	
	
	
	
}
