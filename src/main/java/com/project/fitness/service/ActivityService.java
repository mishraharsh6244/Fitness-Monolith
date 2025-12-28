package com.project.fitness.service;
import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.model.ActivityType;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public List<ActivityResponse> getUserActivities(String userId) {
       // User user = userRepository.findById(userId).orElseThrow(()->  new RuntimeException("Invalid user id"));
        List<Activity> activityList = activityRepository.findByUserId(userId);

      return activityList.stream().map(this::mapResponse).collect(Collectors.toList());


    }

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

       User user =  userRepository.findById(activityRequest.getUserId())
               .orElseThrow(()->new RuntimeException("Invaild id "+ activityRequest.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .activityType(activityRequest.getActivityType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .startTime(activityRequest.getStartTime())
               .build();

          Activity saved = activityRepository.save(activity);
        return mapResponse(saved);

    }

    private  ActivityResponse mapResponse(Activity saved) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setActivityType(saved.getActivityType());
        activityResponse.setId(saved.getId());
        activityResponse.setUserId(saved.getUser().getId() );
        activityResponse.setDuration(saved.getDuration());
        activityResponse.setCaloriesBurned(saved.getCaloriesBurned());
        activityResponse.setAdditionalMetrics(saved.getAdditionalMetrics());
        activityResponse.setStartTime(saved.getStartTime());
        activityResponse.setCreatedAt(saved.getCreatedAt() );
        activityResponse.setUpdatedAt(saved.getUpdatedAt());
        return  activityResponse;
    }
}
