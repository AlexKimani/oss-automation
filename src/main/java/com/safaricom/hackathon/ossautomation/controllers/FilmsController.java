package com.safaricom.hackathon.ossautomation.controllers;

import com.safaricom.hackathon.ossautomation.exception.ResourceNotFoundException;
import com.safaricom.hackathon.ossautomation.pojo.Films;
import com.safaricom.hackathon.ossautomation.pojo.Reviews;
import com.safaricom.hackathon.ossautomation.pojo.Users;
import com.safaricom.hackathon.ossautomation.repository.FilmsRepository;
import com.safaricom.hackathon.ossautomation.repository.ReviewsRepository;
import com.safaricom.hackathon.ossautomation.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/films")
public class FilmsController {
    @Autowired
    private FilmsRepository filmsRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(path = "/create")
    public Films createFilmIndex(@Valid @RequestBody Films films) {
        Users systemUser = usersRepository.findUserByUsername("system");
        String systemUserCode = systemUser.getUserCode().getUserCode();

        films.setCreatedBy(systemUserCode);
        films.setActive(1);

        return filmsRepository.save(films);
    }

    @GetMapping(path = "/all")
    public Page getAllFilms (Pageable pageable) {
        return filmsRepository.findAll(pageable);
    }

    @PostMapping(path = "/rate/{filmid}")
    public Films rateFilm(@PathVariable(value = "filmid") Long filmID, @RequestBody Reviews reviews) {
        Films films = filmsRepository.findById(filmID).orElseThrow(() -> new ResourceNotFoundException("Film", "Film ID", filmID));

        //create a review for it
        Reviews filmReview = new Reviews();
        filmReview.setRating(reviews.getRating());
        filmReview.setFilmID(films.getFilmID());
        filmReview.setWatched(reviews.getWatched());
        filmReview.setReviewer(reviews.getReviewer());
        reviewsRepository.save(filmReview);

        return filmsRepository.findById(filmID).orElseThrow(() -> new ResourceNotFoundException("Film", "Film ID", filmID));
    }

    @GetMapping(path = "/find/{filmid}")
    public Films getFIlmWithReviews (@PathVariable(value = "filmid") Long filmID) {
        return filmsRepository.findById(filmID).orElseThrow(() -> new ResourceNotFoundException("Film", "Film ID", filmID));
    }

    @DeleteMapping(name = "/delete/{filmid}")
    public Page deleteFilmWithReviews (@PathVariable(value = "filmid") Long filmID, Pageable pageable) {
        Films films = filmsRepository.findById(filmID).orElseThrow(() -> new ResourceNotFoundException("Film", "Film ID", filmID));
        //you'll need to delete reviews first bcz of FK
        reviewsRepository.deleteReviewsByFilmID(filmID);
        filmsRepository.deleteById(filmID);

        return filmsRepository.findAll(pageable);
    }
}
