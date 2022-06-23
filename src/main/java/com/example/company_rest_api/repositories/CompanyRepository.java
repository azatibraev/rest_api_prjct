package com.example.company_rest_api.repositories;

import com.example.company_rest_api.dto.response.CompanyResponse;
import com.example.company_rest_api.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Azat Ibraev
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
