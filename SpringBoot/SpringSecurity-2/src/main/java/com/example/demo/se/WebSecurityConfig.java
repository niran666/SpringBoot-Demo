package com.example.demo.se;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;		//认证成功处理类
    @Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    AjaxSessionInformationExpiredStrategy ajaxSessionInformationExpiredStrategy;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        });
    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 如果有允许匿名的url，填在下面
////                .antMatchers().permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // 设置登陆页
//                .formLogin().loginPage("/login")
//                // 设置登陆成功页
//                .defaultSuccessUrl("/").permitAll()
//                // 自定义登陆用户名和密码参数，默认为username和password
////                .usernameParameter("username")
////                .passwordParameter("password")
//                .and()
//                .logout().permitAll();
//
//        // 关闭CSRF跨域
//        http.csrf().disable();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/register","/timeout").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(myAuthenctiationSuccessHandler) // 自定义登录成功处理
                .failureHandler(myAuthenctiationFailureHandler)
                .and()
            .logout()
                .permitAll();
        http.sessionManagement().invalidSessionUrl("/timeout")
                .and().sessionManagement().maximumSessions(1)
                /**
                 * 自定义session过期策略，替代默认的{@link ConcurrentSessionFilter.ResponseBodySessionInformationExpiredStrategy}，
                 * 复写onExpiredSessionDetected方法，默认方法只输出异常，没业务逻辑。这里需要返回json
                 */
                .expiredSessionStrategy(ajaxSessionInformationExpiredStrategy);
        http.csrf().disable();
    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}