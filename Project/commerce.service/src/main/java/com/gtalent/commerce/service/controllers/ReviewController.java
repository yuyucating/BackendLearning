package com.gtalent.commerce.service.controllers;

import java.util.List;

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

import com.gtalent.commerce.service.models.Review;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateReviewRequest;
import com.gtalent.commerce.service.requests.IdListRequest;
import com.gtalent.commerce.service.requests.UpdateReviewStatusRequest;
import com.gtalent.commerce.service.responses.ReviewsResponse;
import com.gtalent.commerce.service.services.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/reviews")
@CrossOrigin("*")
@Tag(name="Review Controller")
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

    @Operation(summary="Update review(s) status",
    description="This API updates review(s) with specified review ids.")
    @PutMapping("/status")
    public ResponseEntity<List<ReviewsResponse>> updateReviews(@RequestBody IdListRequest request,
    @Parameter(
        description="Review status",
        schema=@Schema(allowableValues = {"Accept", "Reject"})
    )@RequestParam(required=true) String status){
        List<Review> reviews = reviewService.updateReviews(request, status);

        if(reviews!=null && !reviews.isEmpty()){
            return ResponseEntity.ok(reviews.stream().map(ReviewsResponse::new).toList());
        }
        return ResponseEntity.noContent().build();
    }
}
