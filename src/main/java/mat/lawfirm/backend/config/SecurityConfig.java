package mat.lawfirm.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@ComponentScan
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .cors()
                    .and()
                .csrf()
                .disable()
                .authorizeRequests()
                    .mvcMatchers(HttpMethod.GET, "/**").permitAll()
                    .mvcMatchers(HttpMethod.POST, "/api/blogComments/**").permitAll()
                    .mvcMatchers(HttpMethod.POST, "/api/sendMail").permitAll()
                .and()
                .addFilterAt(new HeaderAuthenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                    .mvcMatchers( "/**").hasAuthority("admin")
                .and()
                    .httpBasic();
    }

}
