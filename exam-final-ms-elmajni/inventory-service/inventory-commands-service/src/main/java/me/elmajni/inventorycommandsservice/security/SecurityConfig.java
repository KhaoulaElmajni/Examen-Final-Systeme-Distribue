package me.elmajni.inventorycommandsservice.security;

/*import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;*/

//@KeycloakConfiguration//=> @Configuration + @EnableWebSecurity + @EnableGlobalMethodSecurity(prePostEnabled = true)
//=> il permet de configurer la sécurité de l'application
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {//=> il permet de configurer la sécurité de l'application
public class SecurityConfig {
/*@Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        //=> il permet de configurer la stratégie d'authentification
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
        //=> ce n'est pas à moi de gérer les users et les roles
        // on va déléguer l'authentification à un provider => à keycloak
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //=> il permet de configurer les autorisations = les droits d'accès
        super.configure(http);

        http.csrf().disable();
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.headers().frameOptions().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }*/
}
