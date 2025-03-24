package com.example.coreshield_assessmentwithdatabase.controller;

import com.example.coreshield_assessmentwithdatabase.model.MergedLocation;
import com.example.coreshield_assessmentwithdatabase.service.MapDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MapDataController {
    private final MapDataService mapDataService;

    public MapDataController(MapDataService mapDataService) {
        this.mapDataService = mapDataService;
    }

    // Merged data when id is same
    @GetMapping("/mergedData")
    public List<MergedLocation> processData() throws Exception {
        return mapDataService.loadAndMergeData();  // ✅ Load and return merged data
    }

    // Count
    @GetMapping("/count")
    public Map<String, Long> getCountPerType() throws Exception {
        return mapDataService.countValidPointsPerType();  // ✅ No arguments needed
    }

    // Average Rating Per Type
    @GetMapping("/average-rating")
    public Map<String, Double> getAverageRating() throws Exception {
        return mapDataService.calculateAverageRating();  // ✅ No arguments needed
    }

    // Highest Reviews
    @GetMapping("/highest-reviews")
    public MergedLocation getHighestReviewedLocation() throws Exception {
        return mapDataService.getHighestReviewedLocation();  // ✅ No arguments needed
    }

    // Incomplete data
    @GetMapping("/incomplete")
    public List<MergedLocation> getIncompleteData() throws Exception {
        return mapDataService.getIncompleteData();  // ✅ No arguments needed
    }
}
