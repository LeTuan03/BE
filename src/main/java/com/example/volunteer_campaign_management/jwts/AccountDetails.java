package com.example.volunteer_campaign_management.jwts;

import com.example.volunteer_campaign_management.entities.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails implements UserDetails {
    private int accountId;
    private String email;
    private String password;
    private Byte status;
    private GrantedAuthority authority;
    private String firstname;
    private String lastname;

    public static AccountDetails build(AccountEntity accountEntity) {
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountId(accountEntity.getAccountId());
        accountDetails.setEmail(accountEntity.getEmail());
        accountDetails.setPassword(accountEntity.getPassword());
        accountDetails.setStatus(accountEntity.getStatus());
        accountDetails.setAuthority(new SimpleGrantedAuthority(accountEntity.getRoleEntity().getName()));
        return accountDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.authority); // Hoặc trả về danh sách GrantedAuthority nếu có nhiều roles
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
