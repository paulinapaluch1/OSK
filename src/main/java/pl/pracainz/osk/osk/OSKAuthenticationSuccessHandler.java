package pl.pracainz.osk.osk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import pl.pracainz.osk.osk.entity.UserPrincipal;

@Configuration
public class OSKAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			List<String> loggedUserRoles = ((UserPrincipal) principal).getRoleList();

			if (loggedUserRoles.contains("ADMIN")) {
				httpServletResponse.sendRedirect("/");
			} else if (loggedUserRoles.contains("STUDENT")) {
				httpServletResponse.sendRedirect("/students/profile");
			} else if (loggedUserRoles.contains("INSTRUCTOR")) {
				httpServletResponse.sendRedirect("/instructors/profile");
			} else
				httpServletResponse.sendRedirect("/login?error");
		} else
			httpServletResponse.sendRedirect("/login?/error");
	}
}
