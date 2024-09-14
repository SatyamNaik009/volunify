package com.backend.opportunity.entity;

import com.backend.enrollment.entity.Enrollment;
import com.backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="opportunities")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column
    private String category;

    @Column
    private String location;
    @Column
    private String city;


    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private User user;

    @Column
    private String skillsRequired;

    @Column
    private Integer volunteerCount;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String photoUrl;

    @OneToMany(mappedBy = "opportunity", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

}
