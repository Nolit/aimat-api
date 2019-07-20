package net.nolit.dredear.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletResponse
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*
import org.springframework.security.web.AuthenticationEntryPoint




@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        if (http === null) {
            return
        }

        http.csrf().disable()
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/context/time").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())

        http.formLogin()
            .successHandler { _, res, _ ->
                res.setStatus(HttpServletResponse.SC_OK)
                res.writer.print("OK")
                res.getWriter().flush()
            }
            .loginProcessingUrl("/login") // 認証処理を起動させるパス
            .loginPage("/loginForm") // ログインフォームのパス
            .failureUrl("/loginForm/?error") // ログイン処理失敗時の遷移先
//            .defaultSuccessUrl("/tasks") // 認証成功時の遷移先
            .usernameParameter("email").passwordParameter("password")
            .permitAll()
            .and().cors()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Configuration
    protected class AuthenticationConfiguration(
        private val userDetailsService: JpaUserDetailsServiceImpl
    )  : GlobalAuthenticationConfigurerAdapter(){

        @Throws(Exception::class)
        override fun init(auth: AuthenticationManagerBuilder) {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(BCryptPasswordEncoder())
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = Arrays.asList("http://shimasho.nolit.net", "http://localhost:3000")
        configuration.allowedMethods = Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE")
        configuration.allowedHeaders = Arrays.asList("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return SimpleAuthenticationEntryPoint()
    }
}