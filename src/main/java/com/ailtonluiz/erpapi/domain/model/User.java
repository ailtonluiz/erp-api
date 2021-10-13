package com.ailtonluiz.erpapi.domain.model;

import com.ailtonluiz.erpapi.domain.model.Address;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String firstName;
    private String lastName;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime registerDate;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updateDate;


    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();


    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
