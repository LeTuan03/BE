package com.example.volunteer_campaign_management.services;

import com.example.volunteer_campaign_management.dtos.DonorDTO;

import java.util.List;
import java.util.Optional;

public interface DonorService {
    List<DonorDTO> getAllDonor();
    DonorDTO createDonor(DonorDTO donorDTO);
    DonorDTO updateDonor(int donorID, DonorDTO donorDTO);


    boolean deleteDonor(int donorID);

    List<DonorDTO> searchDonor(Optional<String> query);

    DonorDTO getDonorById(int donorID);
    List<DonorDTO> searchByName(String name);
}
