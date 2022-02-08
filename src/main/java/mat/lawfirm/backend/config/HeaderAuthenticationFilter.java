package mat.lawfirm.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


@Component
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    public static String prop;

    @Value("${spring.authorizationToken}")
    public void setProp(String prop) {
        HeaderAuthenticationFilter.prop = prop;
    }

    public HeaderAuthenticationFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var Auth = request.getHeader("Authorization");

        if (Auth != null && Auth.equals(prop)) {
            var user = new User("admin",
                    "password",
                    true,
                    true,
                    true,
                    true,
                    Collections.singletonList(new SimpleGrantedAuthority("admin")));

            final var authentication = new UsernamePasswordAuthenticationToken(user,
                    null,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
