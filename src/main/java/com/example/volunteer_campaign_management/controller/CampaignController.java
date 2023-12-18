package com.example.volunteer_campaign_management.controller;

import com.example.volunteer_campaign_management.dtos.CampaignDTO;
import com.example.volunteer_campaign_management.dtos.RequestVolunteerDTO;
import com.example.volunteer_campaign_management.dtos.StoryDTO;
import com.example.volunteer_campaign_management.entities.CampaignEntity;
import com.example.volunteer_campaign_management.services.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/volunteer-campaign-management/api/v1")
@AllArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;
    public static Timestamp convertStringToTimestamp(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(dateString);
        return new Timestamp(date.getTime());
    }
    @PostMapping("/createCampaign")
    public ResponseEntity<Object> createNewCampaign(@RequestParam("name") String name,
                                                    @RequestParam("start_date") String start_date,
                                                    @RequestParam("end_date") String end_date,
                                                    @RequestParam("desc") String desc,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("location") String location,
                                                    @RequestParam("image") MultipartFile image,
                                                    @RequestParam("currentStatus") int currentStatus) {
        try {
            Timestamp startDate = convertStringToTimestamp(start_date);
            Timestamp endDate = convertStringToTimestamp(end_date);

            return campaignService.createNewCampaign(name, startDate, endDate, desc, title, location, image, currentStatus);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


     @PutMapping("/updateCampaign/{id}")
    public ResponseEntity<Object> updateCampaign(@PathVariable(value = "id") int campaignId,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("start_date") String start_date,
                                                 @RequestParam("end_date") String end_date,
                                                 @RequestParam("desc") String desc,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("location") String location,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam("currentStatus") int currentStatus) {
         try {
             Timestamp startDate = convertStringToTimestamp(start_date);
             Timestamp endDate = convertStringToTimestamp(end_date);
             return campaignService.updateCampaign(campaignId,name, startDate, endDate, desc, title, location, image, currentStatus);
         } catch (ParseException e) {
             e.printStackTrace();
             return null;
         }
    }

//    @PutMapping("/updateCampaign/{id}")
//    public CampaignDTO updateCampaign(@PathVariable(value = "id") @Valid int storyID, @RequestBody CampaignDTO campaignDTO) {
//        return campaignService.updateCampaign(storyID,campaignDTO);
//    }

    @GetMapping("/campaign/{id}")
    public CampaignDTO getCampaignById(@PathVariable(value = "id") int campaignId) {
        return campaignService.getCampaignById(campaignId);
    }

    @PutMapping("/campaign/updateCampaignStatus")
    public CampaignDTO updateCampaignStatus(@RequestBody CampaignDTO campaignDTO) {

        return campaignService.updateCampaignStatus(campaignDTO);
    }

    @GetMapping("/campaigns")
    public java.util.List<CampaignDTO> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping (value = {"campaign/searchCampaign","campaign/searchCampaign/{query}"})
    public java.util.List<CampaignDTO> searchCampaign(@PathVariable(value = "query") Optional<String> query){
        return campaignService.searchCampaign(query);
    }

}
