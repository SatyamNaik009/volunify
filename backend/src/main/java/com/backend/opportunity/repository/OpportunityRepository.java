package com.backend.opportunity.repository;

import com.backend.opportunity.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
    List<Opportunity> findAllByUserId(Long userId);

    @Query("SELECT DISTINCT o.city FROM Opportunity o WHERE o.city IS NOT NULL")
    List<String> findAllDistinctCities();

    List<Opportunity> findAllByCity(String city);
}
