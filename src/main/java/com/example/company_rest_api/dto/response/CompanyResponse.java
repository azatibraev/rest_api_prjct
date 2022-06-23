package com.example.company_rest_api.dto.response;

import com.example.company_rest_api.models.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter

public class CompanyResponse {

    private Long id;

    private String companyName;

    private String locatedCountry;

    private Integer sizeOfCourse;

    public CompanyResponse() {
    }

    public static CompanyResponse companyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        companyResponse.setSizeOfCourse(company.getCourses().size());
        return companyResponse;
    }

    public static List<CompanyResponse> responseList(List<Company> companyList) {
        List<CompanyResponse> responses = new ArrayList<>();
        for (Company company:companyList) {
            responses.add(companyResponse(company));
        }
        return responses;
    }
}
