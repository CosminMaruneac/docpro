package com.example.docpro.features.review;

import com.example.docpro.features.user.User;
import com.example.docpro.features.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;

  public ReviewDto create(ReviewDto dto) {

    Map<String, Object> neededContent = new HashMap<>();

    User creator = userRepository.getReferenceById(dto.getCreatorId());
    User facilitator = userRepository.getReferenceById(dto.getFacilitatorId());

    neededContent.put("creator", creator);
    neededContent.put("facilitator", facilitator);

    Review review = ReviewMapper.reviewDtoToReview(dto, neededContent);

    return ReviewMapper.reviewToReviewDto(reviewRepository.save(review));
  }

  public List<ReviewDto> getByFacilitatorId(Long facilitatorId) {

    User user = userRepository.getReferenceById(facilitatorId);

    return reviewRepository.findAllByFacilitator(user).stream()
        .map(ReviewMapper::reviewToReviewDto)
        .collect(Collectors.toList());
  }
}
