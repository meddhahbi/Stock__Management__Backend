package com.example.gestionStock.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.gestionStock.Services.auth.ApplicationUserDetailsService;
import com.example.gestionStock.Utils.JwtUtil;


@Component
public class ApplicationRequestFilter extends OncePerRequestFilter{

	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private ApplicationUserDetailsService userDetailsService;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		
		
		
			final String authHeader = request.getHeader("Authorization");
			
			String userEmail = null;
			String jwt = null;
			
			String idEntreprise = null;
			
			
			if(StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer")) {
				
				
				jwt = authHeader.substring(7);
				
				userEmail = jwtUtil.extractUsername(jwt); 
				
				idEntreprise = jwtUtil.extractIdEntreprise(jwt);
			}
			
			
			
			
			if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
				
				if(jwtUtil.validateToken(jwt, userDetails)) {
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					
					
					usernamePasswordAuthToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
					
					
				}
				
				
			}
			
			MDC.put("idEntreprise", idEntreprise);
			chain.doFilter(request, response);
			
	}

}
