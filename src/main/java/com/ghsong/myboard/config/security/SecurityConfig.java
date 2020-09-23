package com.ghsong.myboard.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()              // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable()                   // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // jwt token으로 인증하므로 세션은 생성안함
                .and()
                    .authorizeRequests()            // 다음 리퀘스트에 대한 사용권한 체크
                        .antMatchers("/signup", "/signin").permitAll()          // 가입 및 인증 주소는 누구나 접근 가능하도록
                        .antMatchers(HttpMethod.GET, "/test/**").permitAll()        // test로 시작하는 GET요청 리소스는 누구나 접근 가능
                        .anyRequest().hasRole("USER")                                            // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)     // jwt token 필터는 id/password 인증 필터전에
        ;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/h2-console/**",
                        "/v2/api-docs", "/swagger-ui.html/**", "/swagger/**", "/swagger-resources/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
