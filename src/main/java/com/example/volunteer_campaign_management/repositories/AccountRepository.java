package com.example.volunteer_campaign_management.repositories;

import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.entities.DepartmentEntity;
import com.example.volunteer_campaign_management.entities.ProfileEntity;
import com.example.volunteer_campaign_management.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Query(value = "SELECT * FROM user", nativeQuery = true)
    List<AccountEntity> getAllAccounts();

    @Query(value = "select * from user where user_id = ?1", nativeQuery = true)
    AccountEntity findByAccountID(int ID);
    List<AccountEntity> findByEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(Optional<String> query,Optional<String> query1);
    List<AccountEntity> findByProfileEntityFirstnameContainingIgnoreCase(Optional<String> query);
    Boolean existsByEmail(String email);
    List<AccountEntity> findByProfileEntity(ProfileEntity profileEntity);
    List<AccountEntity> findByRoleEntity(RoleEntity roleEntity);
    List<AccountEntity> findByDepartmentEntity(DepartmentEntity departmentEntity);
    AccountEntity findByEmail(String email);

    boolean existsByEmailAndAccountIdNot(String email, int accountId);

}

