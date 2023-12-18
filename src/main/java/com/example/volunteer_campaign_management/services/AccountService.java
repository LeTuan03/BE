package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.AccountDTO;
import com.example.volunteer_campaign_management.entities.AccountEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    ResponseEntity<Object> createNewAccount(String firstName, String lastName, MultipartFile avatar, String address, String email, String phone, String passWord, int roleID, int departmentId);
    AccountDTO getAccountById(int accountId);
    AccountDTO updateAccount(int accountId, AccountDTO updatedAccountDTO);
    AccountEntity save(AccountEntity accountEntity);
    void enableAccount(String email);
    void disableAccount(String email);

    ResponseEntity<Object> changePass(int accountID, String currentPass, String newPass);
    List<AccountDTO> searchAccount(Optional<String> query);
}
