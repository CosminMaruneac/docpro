package com.example.docpro.features.review;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@AllArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping(path = "")
  public ReviewDto create(@RequestBody ReviewDto dto) {

    return reviewService.create(dto);
  }

  @GetMapping(path = "/{facilitatorId}")
  public List<ReviewDto> getByFacilitatorId(@PathVariable(name = "facilitatorId") Long facilitatorId){

    return reviewService.getByFacilitatorId(facilitatorId);
  }
}
