package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.jwts.AccountDetails;
import com.example.volunteer_campaign_management.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByEmail(email);

        if (accountEntity == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(accountEntity.getPassword());
        accountEntity.setPassword(encodedPassword);

        return AccountDetails.build(accountEntity);
    }
}
