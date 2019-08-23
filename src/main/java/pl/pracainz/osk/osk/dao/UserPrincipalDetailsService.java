package pl.pracainz.osk.osk.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.entity.UserPrincipal;

@Service
public  class UserPrincipalDetailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	
	public UserPrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return  userPrincipal;
	}
	
	
	
	
}