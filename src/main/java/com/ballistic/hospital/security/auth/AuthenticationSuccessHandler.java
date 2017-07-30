package com.ballistic.hospital.security.auth;

/**
 * Created by fan.jin on 2016-11-07.
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ballistic.hospital.dto.DoctorTokenState;
import com.ballistic.hospital.entity.Doctor;
import com.ballistic.hospital.security.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

	@Autowired
    TokenHelper tokenHelper;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication ) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		Doctor doctor = (Doctor) authentication.getPrincipal();

		String jws = tokenHelper.generateToken( doctor.getUsername() );

        // Create token auth Cookie
        Cookie authCookie = new Cookie( TOKEN_COOKIE, ( jws ) );
		authCookie.setPath( "/" );
		authCookie.setHttpOnly( true );
		authCookie.setMaxAge( EXPIRES_IN );
		// Add cookie to response
		response.addCookie( authCookie );
		// JWT is also in the response
		DoctorTokenState userTokenState = new DoctorTokenState(jws, doctor , EXPIRES_IN);
		String jwtResponse = objectMapper.writeValueAsString( userTokenState );
		response.setContentType("application/json");
		response.getWriter().write( jwtResponse );

	}
}
