package com.stockMarket.controller;


import com.stockMarket.entities.Company;
import com.stockMarket.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;


	@GetMapping("getAllCompanies")
//	@Cacheable(value = "company")
	public List<Company> getAllAirlines() {
		return this.companyService.getCompanies();
	}

	@PostMapping("saveCompanies")
//	@Cacheable(value = "company")
	public void saveCompanies(@RequestBody List<Company> companies) {
		 this.companyService.saveCompanies(companies);
	}

	@GetMapping("getCompany/{companyCode}")

	public List<Company> getCompany(@PathVariable("companyCode") String companyCode) {
		List<Company> companyList= this.companyService.getCompanies();
		List<Company> stockCompanyList=companyList.stream().filter(x->x.getCompanyCode().equalsIgnoreCase(companyCode)).collect(Collectors.toList());
		return stockCompanyList;
	}

}
