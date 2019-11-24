package pl.pracainz.osk.osk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.entity.UserPrincipal;

public class PasswordChanger {

	String newPassword;
	String oldPassword;
	String repeatedPassword;

	@Autowired
	UserRepository userRepository;

	
	@Autowired 
	PasswordEncoder encoder;
	
	public PasswordChanger() {
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public boolean canPasswordBeChanged() {
		if(getNewPassword()!=null && getRepeatedPassword()!=null) {
			if(getNewPassword().contentEquals(getRepeatedPassword()))
			return true;
		else
			return false;
			}
	
	else return false;

}}
