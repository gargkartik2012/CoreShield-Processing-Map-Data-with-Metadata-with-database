package com.example.coreshield_assessmentwithdatabase.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.coreshield_assessmentwithdatabase.model.Location;
import com.example.coreshield_assessmentwithdatabase.model.Metadata;
import com.example.coreshield_assessmentwithdatabase.model.MergedLocation;
import com.example.coreshield_assessmentwithdatabase.repository.LocationRepository;
import com.example.coreshield_assessmentwithdatabase.repository.MetadataRepository;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapDataService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final LocationRepository locationRepository;
    private final MetadataRepository metadataRepository;
    private List<MergedLocation> cachedMergedData = null;  // ✅ Store merged data

    public MapDataService(LocationRepository locationRepository, MetadataRepository metadataRepository) {
        this.locationRepository = locationRepository;
        this.metadataRepository = metadataRepository;
    }

    // Merge Data (Store in Memory)
    public List<MergedLocation> loadAndMergeData() throws Exception {
        if (cachedMergedData != null) {
            return cachedMergedData;  // ✅ Prevent reloading if already in memory
        }


        InputStream locationStream = getClass().getClassLoader().getResourceAsStream("locations.json");
        InputStream metadataStream = getClass().getClassLoader().getResourceAsStream("metadata.json");

        if (locationStream == null || metadataStream == null) {
            throw new RuntimeException("JSON files not found in resources folder!");
        }


        List<Location> locations = objectMapper.readValue(locationStream, new TypeReference<>() {});
        List<Metadata> metadataList = objectMapper.readValue(metadataStream, new TypeReference<>() {});


        locationRepository.saveAll(locations);
        metadataRepository.saveAll(metadataList);


        Map<String, Location> locationMap = locations.stream()
                .collect(Collectors.toMap(Location::getId, loc -> loc));

        // Merge data when ID matches
        cachedMergedData = metadataList.stream()
                .filter(m -> locationMap.containsKey(m.getId())) // Only merge if metadata exists
                .map(m -> new MergedLocation(locationMap.get(m.getId()), m))
                .toList();

        return cachedMergedData;
    }

    // Count Valid Points Per Type
    public Map<String, Long> countValidPointsPerType() throws Exception {
        loadAndMergeData();  // ✅ Ensure data is loaded
        return cachedMergedData.stream()
                .filter(m -> m.getMetadata() != null && m.getMetadata().getType() != null)
                .collect(Collectors.groupingBy(m -> m.getMetadata().getType(), Collectors.counting()));
    }

    // Average Rating Per Type
    public Map<String, Double> calculateAverageRating() throws Exception {
        loadAndMergeData();
        return cachedMergedData.stream()
                .filter(m -> m.getMetadata() != null && m.getMetadata().getRating() > 0)
                .collect(Collectors.groupingBy(m -> m.getMetadata().getType(),
                        Collectors.averagingDouble(m -> m.getMetadata().getRating())));
    }

    //  Highest Number of Reviews
    public MergedLocation getHighestReviewedLocation() throws Exception {
        loadAndMergeData();
        return cachedMergedData.stream()
                .filter(m -> m.getMetadata() != null)
                .max(Comparator.comparingInt(m -> m.getMetadata().getReviews()))
                .orElse(null);
    }

    // Incomplete Data (Bonus)
    public List<MergedLocation> getIncompleteData() throws Exception {
        loadAndMergeData();
        return cachedMergedData.stream()
                .filter(m -> m.getMetadata() == null ||
                        m.getMetadata().getType() == null ||
                        m.getMetadata().getRating() == 0 ||
                        m.getMetadata().getReviews() == 0)
                .toList();
    }
}
