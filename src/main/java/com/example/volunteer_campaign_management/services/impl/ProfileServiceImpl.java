package com.example.volunteer_campaign_management.services.impl;


import com.example.volunteer_campaign_management.dtos.AccountDTO;
import com.example.volunteer_campaign_management.dtos.ProfileDTO;
import com.example.volunteer_campaign_management.entities.AccountEntity;
import com.example.volunteer_campaign_management.entities.ProfileEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.AccountRepository;
import com.example.volunteer_campaign_management.repositories.ProfileRepository;
import com.example.volunteer_campaign_management.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final MapperUtil mapperUtil;
    private  final CloudinaryService cloudinaryServicel;

    public ProfileServiceImpl(ProfileRepository profileRepository, AccountRepository accountRepository, MapperUtil mapperUtil, CloudinaryService cloudinaryServicel) {
        this.profileRepository = profileRepository;
        this.accountRepository = accountRepository;
        this.mapperUtil = mapperUtil;
        this.cloudinaryServicel = cloudinaryServicel;
    }

    static AccountDTO getAccountDTO(AccountDTO accountDTO, AccountEntity accountEntity, AccountRepository accountRepository) {
        if(accountDTO.getAvatar() != null && !accountDTO.getAvatar().isEmpty()){
            accountEntity.getProfileEntity().setAvatar(accountDTO.getAvatar());
        }
        if(accountDTO.getFirstname() != null && !accountDTO.getFirstname().isEmpty()){
            accountEntity.getProfileEntity().setFirstname(accountDTO.getFirstname());
        }
        if(accountDTO.getLastname() != null && !accountDTO.getLastname().isEmpty()){
            accountEntity.getProfileEntity().setLastname(accountDTO.getLastname());
        }
        if(accountDTO.getAddress() != null && !accountDTO.getAddress().isEmpty()){
            accountEntity.getProfileEntity().setAddress(accountDTO.getAddress());
        }
        accountRepository.save(accountEntity);
        return accountDTO;
    }

    @Override
    public ProfileEntity profileById(int accountId) {
        System.out.println(accountId);
        ProfileEntity profileEntity = profileRepository.findById(accountId);
        try {
            return profileEntity;
        } catch (Exception e) {
            e.getMessage();
            return  null;
        }
    }

    @Override
    public ProfileDTO getProfileById(int accountId) {
        try {
            AccountEntity accountEntity = accountRepository.findById(accountId).get();
            ProfileDTO profileDTO = mapperUtil.mapToProfileDTO(accountEntity);
            profileDTO.setFirstname(accountEntity.getProfileEntity().getFirstname());
            profileDTO.setLastname(accountEntity.getProfileEntity().getLastname());
            profileDTO.setAvatar(accountEntity.getProfileEntity().getAvatar());
            profileDTO.setAddress(accountEntity.getProfileEntity().getAddress());
            profileDTO.setPhone(accountEntity.getPhone());
            profileDTO.setEmail(accountEntity.getEmail());
            profileDTO.setRol(accountEntity.getRoleEntity().getName());
            profileDTO.setDepartment(accountEntity.getDepartmentEntity().getName());

            return profileDTO;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    @Override
    public ResponseEntity<Object> updateProfile(int accountId, String firstName, String lastName, String email, String phone, String address, MultipartFile avatar) {
        try{
            AccountEntity accountEntity = accountRepository.findById(accountId).get();
            int profileID = accountEntity.getProfileEntity().getProfileId();
            ProfileEntity updateProfile =profileRepository.findById(profileID);
            if(firstName.isEmpty()) {
                updateProfile.setFirstname(updateProfile.getFirstname());
            } else {
                updateProfile.setFirstname(firstName);
            }
            if(lastName.isEmpty()) {
                updateProfile.setLastname(updateProfile.getLastname());
            } else {
                updateProfile.setLastname(lastName);
            }
            if(address.isEmpty()) {
                updateProfile.setAddress(updateProfile.getAddress());
            } else {
                updateProfile.setAddress(address);
            }
            if(email.isEmpty()) {
                accountEntity.setEmail(accountEntity.getEmail());
            } else {
                accountEntity.setEmail(email);
            }
            if(phone.isEmpty()) {
                accountEntity.setPhone(accountEntity.getPhone());
            } else {
                accountEntity.setPhone(phone);
            }
            if(avatar.isEmpty()) {
                updateProfile.setAvatar(updateProfile.getAvatar());
            } else {
                updateProfile.setAvatar(cloudinaryServicel.uploadImage(avatar));
            }
            accountRepository.save(accountEntity);
            profileRepository.save(updateProfile);

            ProfileDTO response = new ProfileDTO();
            response.setAddress(updateProfile.getAddress());
            response.setAvatar(updateProfile.getAvatar());
            response.setFirstname(updateProfile.getFirstname());
            response.setLastname(updateProfile.getLastname());
            response.setEmail(accountEntity.getEmail());
            response.setPhone(accountEntity.getPhone());
            response.setRol(accountEntity.getRoleEntity().getName());
            response.setDepartment(accountEntity.getDepartmentEntity().getDescription());
            return new ResponseEntity<>(accountEntity, HttpStatus.OK);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}
