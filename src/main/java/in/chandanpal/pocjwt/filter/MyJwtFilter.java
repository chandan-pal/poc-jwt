package in.chandanpal.pocjwt.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.chandanpal.pocjwt.model.User;
import in.chandanpal.pocjwt.service.UserService;
import in.chandanpal.pocjwt.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
//We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
public class MyJwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
	private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwtToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            userEmail = jwtUtil.extractUserEmail(jwtToken);
        }
        
        System.out.println("TEST:: jwtToken=" + jwtToken);//debug
        System.out.println("TEST:: userEmail=" + userEmail);//debug

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userService.findUserByEmail(userEmail);
            System.out.println("TEST:: user=" + user);//debug
            if (jwtUtil.validateToken(jwtToken, user)) {
            	System.out.println("TEST:: validated token");//debug
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, new ArrayList<>());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        System.out.println("TEST:: chain...");//debug
        chain.doFilter(request, response);
    }

}
