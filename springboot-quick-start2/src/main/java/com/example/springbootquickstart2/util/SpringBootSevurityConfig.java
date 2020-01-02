package com.example.springbootquickstart2.util;//package com.example.springbootquickstart2.tools;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SpringBootSevurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    //定义授权规则
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
//
//        http.authorizeRequests().antMatchers("/").permitAll();
////                .antMatchers("/selStudent/**").hasRole("VIP1")
////                .antMatchers("/saveStudent/**").hasRole("VIP2");
//        //开启自动配置的登录功能
//        http.formLogin();
//        //1.login来到登录页
//        //2.重定向到/Login?error表示登录失败
//        //3.
//
//        //开启自动配置的注销功能
//        http.logout().logoutSuccessUrl("/");
//        //访问/logout表示用户注销，清空session
//    }
//
//    //定义认证规则
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").password(passwordEncoder().encode("123456")).roles("VIP1")
//                .and()
//                .withUser("admin2").password("123456").roles("VIP2");
//    }
//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
