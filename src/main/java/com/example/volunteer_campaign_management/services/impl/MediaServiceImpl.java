package com.example.volunteer_campaign_management.services.impl;

import com.example.volunteer_campaign_management.dtos.MediaDTO;
import com.example.volunteer_campaign_management.entities.MediaCampaignEntity;
import com.example.volunteer_campaign_management.mappers.MapperUtil;
import com.example.volunteer_campaign_management.repositories.MediaCampaignRepository;
import com.example.volunteer_campaign_management.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    private final MapperUtil mapperUtil;

    @Autowired
    private MediaCampaignRepository mediaRepository;

    public MediaServiceImpl(MapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;
    }


    @Override
    public boolean deleteMedia(int mediaId) {
        try {
            if (mediaRepository.getOne(mediaId) != null) {
                mediaRepository.deleteById(mediaId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

    }

    @Override
    public MediaDTO getMediaById(int mediaId) {

        try {
            MediaCampaignEntity optionalEntity = mediaRepository.findById(mediaId).get();
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setImage(optionalEntity.getImage());
            mediaDTO.setVideo(optionalEntity.getVideo());
            mediaDTO.setCampaignId(optionalEntity.getCampaignEntity().getCampaignId());
            mediaDTO.setCampaignName(optionalEntity.getCampaignEntity().getName());
            mediaDTO.setId(mediaId);
            return mediaDTO;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<MediaDTO> getAllMedia() {
        try{   List<MediaCampaignEntity> mediaEntities = mediaRepository.findAll();
        List<MediaDTO> mediaDTOS = new ArrayList<>();
        mediaEntities.stream().forEach(MediaCampaignEntity->{
            MediaDTO mediaDTO = new MediaDTO(MediaCampaignEntity.getMediaId(),
                    MediaCampaignEntity.getImage(),MediaCampaignEntity.getVideo(),
                    MediaCampaignEntity.getCampaignEntity().getCampaignId(),
                    MediaCampaignEntity.getCampaignEntity().getName());
            mediaDTOS.add(mediaDTO);
        });
            return mediaDTOS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<MediaDTO> searchMedia(Optional<String> keyword) {
        List<MediaCampaignEntity> mediaEntityDTOS = new ArrayList<>();
        try{
            if (!keyword.isPresent()) {
                return getAllMedia();
            }
            else {
                mediaEntityDTOS = mediaRepository.findByImageContainingIgnoreCaseOrVideoContainingIgnoreCase(keyword, keyword);

            }
        } catch (Exception e) {
            e.getMessage();
        }
        return mapperUtil.mapToListMediaDTO(mediaEntityDTOS);
    }
}
