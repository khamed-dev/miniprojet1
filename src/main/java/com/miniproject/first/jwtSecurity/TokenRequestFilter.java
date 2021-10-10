package com.miniproject.first.jwtSecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.miniproject.first.domain.User;
import com.miniproject.first.ervices.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class TokenRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		Long userId=null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			token = requestTokenHeader.substring(7);
			try {
				//username = tokenProvider.getUsernameFromToken(token);
				  userId = tokenProvider.getUserIdFromToken(token);
				  System.out.println(userId);

	                /*
	                    Note that you could also encode the user's username and roles inside JWT claims
	                    and create the UserDetails object by parsing those claims from the JWT.
	                    That would avoid the following database hit. It's completely up to you.
	                 */
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");

			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			//User userDetails = (User) userService.loadUserByUsername(username);
            User userDetails =  userService.loadUserById(userId);
            System.out.println(userDetails.getEmail());

			if (tokenProvider.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}

		chain.doFilter(request, response);
	}

}
