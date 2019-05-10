package com.safaricom.hackathon.ossautomation.repository;

import com.safaricom.hackathon.ossautomation.pojo.Films;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmsRepository extends JpaRepository<Films, Long> {
}
