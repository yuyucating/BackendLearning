package com.gtalent.commerce.service.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gtalent.commerce.service.enums.Rating;
import com.gtalent.commerce.service.enums.ReviewStatus;
import com.gtalent.commerce.service.models.Product;
import com.gtalent.commerce.service.models.Review;
import com.gtalent.commerce.service.models.User;
import com.gtalent.commerce.service.repositories.ProductRepository;
import com.gtalent.commerce.service.repositories.ReviewRepository;
import com.gtalent.commerce.service.repositories.UserRepository;
import com.gtalent.commerce.service.requests.CreateReviewRequest;
import com.gtalent.commerce.service.requests.IdListRequest;
import com.gtalent.commerce.service.requests.UpdateReviewStatusRequest;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Review createReview(Integer rating, CreateReviewRequest request){
        Review review = new Review();
        if(rating!=null){
            switch (rating) {
                case 1:
                    review.setRating(Rating.One);
                    break;
                case 2:
                    review.setRating(Rating.Two);
                    break;
                case 3:
                    review.setRating(Rating.Three);
                    break;
                case 4:
                    review.setRating(Rating.Four);
                    break;
                case 5:
                    review.setRating(Rating.Five);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        Optional<User> users = userRepository.findById(request.getUserId());
        if(!users.isEmpty() && users!=null){
            review.setUser(users.get());
        }else{return null;}
        Optional<Product> product = productRepository.findById(request.getProductId());
        if(product!=null){
            review.setProduct(product.get());
        }
        review.setComment(request.getComment());
        reviewRepository.save(review);
        return review;
    }

    public Page<Review> getAllReviews(String query, PageRequest pageRequest){
        // Specification<User> spec = reviewSpecification(query);
        Set<Review> reviewsSet = new HashSet<>();
        reviewsSet.addAll(reviewRepository.findAll());
        if(query!=null && !query.isEmpty()){
            reviewsSet.retainAll(reviewRepository.findByCommentContainingIgnoreCase(query));
        }

        if (reviewsSet.isEmpty()){
            return null;
        }
        else{
            List<Review> resultList = new ArrayList<>(reviewsSet);
            Page<Review> results = new PageImpl<>(resultList, pageRequest, resultList.size());
            return results;
        }  
    }

    public Page<Review> getProductReviews(Product product, PageRequest pageRequest){
        List<Review> reviews = reviewRepository.findByProduct(product);
        if(reviews!=null && !reviews.isEmpty()){
            Page<Review> results = new PageImpl<>(reviews, pageRequest, reviews.size());
            return results;
        }return null;
    }

    public List<Review> updateReview(UpdateReviewStatusRequest request){
        List<Review> results = new ArrayList<>();
        List<Integer> ids = request.getIds();
        for (Integer id : ids) {
            Optional<Review> review = reviewRepository.findById(id);
            if(review!=null && !review.isEmpty()){
                Review result = review.get();
                result.setStatus(request.getStatus());
                reviewRepository.save(result);

                results.add(result);
            }else{
                return null;
            }
        }
        return results;
    }

    public List<Review> updateReviews(IdListRequest request, String status){
        List<Review> reviews = reviewRepository.findAllById(request.getIds());
        List<Review> results = new ArrayList<>();
        if(reviews!=null && !reviews.isEmpty()){
            for(Review review:reviews){
                review.setStatus(ReviewStatus.valueOf(status));
                reviewRepository.save(review);
                results.add(review);
            }
            return results;
        }return null;
    }
}
