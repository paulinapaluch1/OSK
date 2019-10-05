package pl.pracainz.osk.osk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pracainz.osk.osk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);

	User findById(int userId);
	
	
	
	
	
}
