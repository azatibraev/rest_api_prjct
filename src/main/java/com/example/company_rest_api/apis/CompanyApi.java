package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.CompanyRequest;
import com.example.company_rest_api.dto.response.CompanyResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.services.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("api/companies")
@CrossOrigin
@Tag(name = "Company API")
//@PreAuthorize("isAuthenticated()")
public class CompanyApi {

    private final CompanyService companyService;

    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }


    //findAll
    @GetMapping
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STUDENT','TEACHER'})")
    public List<CompanyResponse> findAllCompanies() {
        return companyService.findAllCompanies();
    }


    // findById
    @GetMapping("/find/{companyId}")
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STUDENT','TEACHER'})")
    public CompanyResponse findById(@PathVariable Long companyId) {
        return companyService.findByCompanyId(companyId);
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    //delete
    @DeleteMapping("/delete/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteByCompanyId(@PathVariable Long companyId) {
        return companyService.deleteByCompanyId(companyId);
    }

    //update
    @PutMapping("/update/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponse updateCompanyById(@PathVariable Long companyId,
                               @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompanyById(companyId,companyRequest);

    }
}
