package com.safaricom.hackathon.ossautomation.repository;

import com.safaricom.hackathon.ossautomation.pojo.VideoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoTypeRepository extends JpaRepository<VideoType, Long> {
//    VideoType findByTypeID(int typeID);
}
