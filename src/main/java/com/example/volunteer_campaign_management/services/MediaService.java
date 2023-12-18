package com.example.volunteer_campaign_management.services;
import com.example.volunteer_campaign_management.dtos.MediaDTO;

import java.util.List;
import java.util.Optional;

public interface MediaService {
    boolean deleteMedia(int mediaId);
    MediaDTO getMediaById(int mediaId);
    List<MediaDTO> getAllMedia();
    List<MediaDTO> searchMedia(Optional<String> keyword);
}
