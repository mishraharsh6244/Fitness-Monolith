package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

//    public Recommendation generateRecommendation(RecommendationRequest request) {
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(()-> new RuntimeException("Invalid UserId"));
//        Activity  activity = activityRepository.findById(request.getActivityId())
//                .orElseThrow(()-> new RuntimeException("Invalid Activity"));
//        return
//

    }
}
