package com.example.company_rest_api.repositories;

import com.example.company_rest_api.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Azat Ibraev
 */
public interface GroupRepository extends JpaRepository<Group, Long> {
}
