package com.example.volunteer_campaign_management.configs;

import com.example.volunteer_campaign_management.filters.AuthTokenFilter;
import com.example.volunteer_campaign_management.jwts.AuthEntryPointJwt;
import com.example.volunteer_campaign_management.services.impl.AccountDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountDetailServiceImpl accountDetailService;
    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(accountDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
                .antMatchers("/volunteer-campaign-management/api/v1/Login").permitAll()
                .antMatchers(HttpMethod.POST,"volunteer-campaign-management/api/v1/image").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/campaigns/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/campaign/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/generalReports/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/generalReport/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/taskReports/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/taskReport/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/issues/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/stories/**").permitAll()
                //account
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/account/changePassword/**").hasAnyRole("Admin", "trưởng nhóm", "trưởng ban kế hoạch", "trưởng ban sự kiện", "kế toán")
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/accounts/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/account/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/changepass").permitAll()
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/admin/account").hasAnyRole("Admin")
                //campaign
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/campaign/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/campaign/**").permitAll()
                //general report
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/createGeneralReport").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/updateGeneralReport").permitAll()
                .antMatchers(HttpMethod.DELETE, "volunteer-campaign-management/api/v1/deleteGeneralReport").permitAll()
                //task report
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/taskReport/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/taskReport/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/volunteer-campaign-management/api/v1/deleteTaskReport/{id}").permitAll()
                //issue
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/createIssue").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/updateIssue").permitAll()
                .antMatchers(HttpMethod.DELETE,"/volunteer-campaign-management/api/v1/deleteIssue/{id}").permitAll()
                //story
                .antMatchers(HttpMethod.POST, "/volunteer-campaign-management/api/v1/story/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/volunteer-campaign-management/api/v1/story/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/volunteer-campaign-management/api/v1/story/**").permitAll()
                // financial Report
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/financialReport/create").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/financialReport/update/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/financialReport/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/financialReport/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/financialReport/search/{query}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/volunteer-campaign-management/api/v1/financialReport/delete/{id}").permitAll()
                // Milestone
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/milestone/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/milestone/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/milestone/update/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/milestone/create").permitAll()
                .antMatchers(HttpMethod.DELETE,"/volunteer-campaign-management/api/v1/milestone/delete/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/milestone/search/{query}").permitAll()
                //Donor
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/donor/create").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/donor/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/donor/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/donor/update/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/volunteer-campaign-management/api/v1/donor/delete/{id}").permitAll()
                // Request volunteer
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/RequestVolunteer/**").permitAll()
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/RequestVolunteer/create").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/RequestVolunteer/updateVolunteerStatus/{id}").permitAll()
                // Add NewController endpoints
                .antMatchers(HttpMethod.POST,"/volunteer-campaign-management/api/v1/news/create").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/news/update/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/volunteer-campaign-management/api/v1/news/delete/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/news/list").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/news/getById/**").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/news/search").permitAll()
                // Profile
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/account/profile/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/volunteer-campaign-management/api/v1/account/updateProfile/{id}").permitAll()
                // VNPay
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/pay").permitAll()
                .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/checkPay2").permitAll()
                //
                .antMatchers("/getAllContributions/**").permitAll()
//
          .antMatchers(HttpMethod.GET,"/volunteer-campaign-management/api/v1/media/list").permitAll();

        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
