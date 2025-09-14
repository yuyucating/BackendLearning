package com.gtalent.commerce.service.controllers;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gtalent.commerce.service.enums.ReviewStatus;
import com.gtalent.commerce.service.models.Review;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.ReviewRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateReviewRequest;
import com.gtalent.commerce.service.requests.UpdateReviewStatusRequest;
import com.gtalent.commerce.service.responses.GetUserListResponse;
import com.gtalent.commerce.service.responses.ReviewsResponse;
import com.gtalent.commerce.service.services.ReviewService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("v1/reviews")
@CrossOrigin("*")
public class ReviewController {
    private final ReviewService reviewService;


    @Autowired
    public ReviewController(ReviewService reviewService, UserRepository userRepository){
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewsResponse> createReview (@RequestBody CreateReviewRequest request,
        @Parameter(
            description = "Rating",
            schema = @Schema(allowableValues = {"★","★★","★★★","★★★★","★★★★★"})
        )@RequestParam(required=false) String rating){
        Review review = reviewService.createReview(rating.length(), request);

        if(review!=null){
            ReviewsResponse response = new ReviewsResponse(review);
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ReviewsResponse>> getAllResponsesPage(
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size,
        @Parameter(description="Keyword for searching by comment content")
        @RequestParam(defaultValue= "") String query
    ){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Review> results = reviewService.getAllReviews(query, pageRequest);
        if(results==null || results.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            Page<ReviewsResponse> response = results.map(ReviewsResponse::new); 
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<List<ReviewsResponse>> getReviewPage(@RequestBody UpdateReviewStatusRequest request){
        List<Review> reviews = reviewService.getReviewPage(request);

        if(reviews!=null && !reviews.isEmpty()){
            return ResponseEntity.ok(reviews.stream().map(ReviewsResponse::new).toList());
        }
        return ResponseEntity.noContent().build();
    }
}
