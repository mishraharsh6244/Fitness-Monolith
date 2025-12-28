package com.project.fitness.controller;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.model.Activity;
import com.project.fitness.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackactivity(@RequestBody ActivityRequest activityRequest){

        return ResponseEntity.ok(activityService.trackActivity( activityRequest));

    }
    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(
          @RequestHeader(value = "X-User-ID")  String UserId
    )
    {
        return ResponseEntity.ok(activityService.getUserActivities(UserId));

    }



}
