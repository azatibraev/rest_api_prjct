package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.CompanyRequest;
import com.example.company_rest_api.dto.response.CompanyResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.exceptions.CompanyNotFoundException;
import com.example.company_rest_api.models.Company;
import com.example.company_rest_api.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

//    public ResponseEntity<Company> saveCompany(Company company) {
//            Company newCompany = companyRepository.save(company);
//            return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
//        }
//
//    public ResponseEntity<Company> findByCompanyId(Long companyId) {
//            Company company = companyRepository.findById(companyId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
//            return new ResponseEntity<>(company, HttpStatus.OK);
//        }
//
//    public ResponseEntity<ApiResponse> deleteByCompanyId(Long companyId) {
//            Company company = companyRepository.findById(companyId)
//        .orElseThrow(() -> new ResourceNotFoundException("company", "id", companyId));
//                companyRepository.deleteById(companyId);
//                return new ResponseEntity<>(new ApiResponse(), HttpStatus.OK);
//            }
//
//    public ResponseEntity<Company> updateCompany(Long companyId, Company newCompany) {
//            Company company1 = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Company", "id", companyId));
//                company1.setCompanyName(newCompany.getCompanyName());
//                company1.setLocatedCountry(newCompany.getLocatedCountry());
//                Company updatedCompany = companyRepository.save(company1);
//                return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
//            }


    public List<CompanyResponse> findAllCompanies() {
        return CompanyResponse.responseList(companyRepository.findAll());
    }

    public CompanyResponse findByCompanyId(Long companyId) {
        Company companyById = getCompanyById(companyId);
        return CompanyResponse.companyResponse(companyById);
    }

    public CompanyResponse save(CompanyRequest companyRequest) {
      Company company = new Company(companyRequest.getCompanyName(),companyRequest.getLocatedCountry());
      companyRepository.save(company);
      return CompanyResponse.companyResponse(company);

    }

    public SimpleResponse deleteByCompanyId(Long companyId) {
        boolean existsById = companyRepository.existsById(companyId);
        if(!existsById) {
            throw new CompanyNotFoundException(
                    "Company with id = " + companyId + " not found!"
            );
        }
         companyRepository.deleteById(companyId);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete company"
        );
    }

    @Transactional
    public CompanyResponse updateCompanyById(Long companyId, CompanyRequest companyRequest) {
        Company company = getCompanyById(companyId);
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        return CompanyResponse.companyResponse(company);
    }

    private Company getCompanyById(Long companyId){
        return companyRepository.findById(companyId).
            orElseThrow(()-> new CompanyNotFoundException(
                    "Company with id = " + companyId + " not found"
            ));
    }
}