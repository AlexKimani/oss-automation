package com.safaricom.hackathon.ossautomation.repository;

import com.safaricom.hackathon.ossautomation.pojo.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    void deleteReviewsByFilmID(Long filmID);
}
