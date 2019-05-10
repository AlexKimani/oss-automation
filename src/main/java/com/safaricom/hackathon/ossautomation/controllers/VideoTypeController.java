package com.safaricom.hackathon.ossautomation.controllers;

import com.safaricom.hackathon.ossautomation.exception.ResourceNotFoundException;
import com.safaricom.hackathon.ossautomation.pojo.Users;
import com.safaricom.hackathon.ossautomation.pojo.VideoType;
import com.safaricom.hackathon.ossautomation.repository.UsersRepository;
import com.safaricom.hackathon.ossautomation.repository.VideoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/video/type")
public class VideoTypeController {
    @Autowired
    private VideoTypeRepository videoTypeRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(path = "/all")
    public Page getAllVideoTypes (Pageable pageable) {
        return videoTypeRepository.findAll(pageable);
    }

    @PostMapping(path = "/create")
    public VideoType createVideoType (@Valid @RequestBody VideoType videoType) {
        Users systemUser = usersRepository.findUserByUsername("system");
        String systemUserCode = systemUser.getUserCode().getUserCode();

        videoType.setCreatedBy(systemUserCode);
        videoType.setActive(1);
        return videoTypeRepository.save(videoType);
    }

    @GetMapping(path = "/find/{id}")
    public VideoType getVideoTypeByName (@PathVariable (value = "id") Long typeID) {
        return videoTypeRepository.findById(typeID).orElseThrow(() -> new ResourceNotFoundException("Video Type", "TypeID", typeID));
    }

    @PutMapping(path = "/update/{id}")
    public VideoType updateVideoType (@PathVariable(value = "id") Long typeID, @RequestBody VideoType videoType) {
        VideoType newVideoType = videoTypeRepository.findById(typeID).orElseThrow(() -> new ResourceNotFoundException("Video Type", "TypeID", typeID));

        newVideoType.setTypeName(videoType.getTypeName());
        Users systemUser = usersRepository.findUserByUsername("system");
        newVideoType.setUpdatedBy(systemUser.getUserCode().getUserCode());

        return videoTypeRepository.save(videoType);
    }

    @DeleteMapping(path = "/delete/{id}")
    public Page deleteVideoType (@PathVariable(name = "id") Long typeID, Pageable pageable) {
        VideoType videoType = videoTypeRepository.findById(typeID).orElseThrow(() -> new ResourceNotFoundException("Video Type", "TypeID", typeID));

        videoTypeRepository.deleteById(videoType.getTypeID());
        return videoTypeRepository.findAll(pageable);
    }
}
