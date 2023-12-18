package com.example.volunteer_campaign_management.controller;


import com.example.volunteer_campaign_management.dtos.DonorDTO;
import com.example.volunteer_campaign_management.services.DonorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class DonorController {
    @Autowired
    private final DonorService donorService;
    @GetMapping("/donor/list")
    public List<DonorDTO> getAllDonor() {
        return donorService.getAllDonor();
    }

    @PostMapping("/donor/create")
    public DonorDTO createDonor(@RequestBody @Valid DonorDTO donorDTO) {
        return donorService.createDonor(donorDTO);
    }

    @PutMapping("/donor/update/{id}")
    public DonorDTO updateDonor(@PathVariable(value = "id") @Valid int donorID, @RequestBody DonorDTO donorDTO){
        return donorService.updateDonor(donorID,donorDTO);
    }

    @GetMapping("/donor/{id}")
    public DonorDTO getDonorById(@PathVariable(value = "id") int donorID){

        return donorService.getDonorById(donorID);
    }

    @DeleteMapping("/donor/delete/{id}")
    public boolean deleteDonor(@PathVariable(value = "id") int donorID) {

        return donorService.deleteDonor(donorID);
    }

    @GetMapping (value = {"donor/search","donor/search/{query}"})
    public List<DonorDTO> searchDonor(@PathVariable(value = "query") Optional<String> query){
        return donorService.searchDonor(query);
    }
    @GetMapping("/searchDonor")
    public List<DonorDTO> searchByname(@RequestParam("name") String name)
    {
        return donorService.searchByName(name);
    }
}
