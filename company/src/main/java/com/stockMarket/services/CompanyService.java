package com.stockMarket.services;

import com.stockMarket.entities.Company;
import com.stockMarket.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getCompanies(){
		return companyRepository.findAll();
	}

	public void saveCompanies(List<Company> companies){
		for(Company company : companies)
			company.setDate(new Date());
		this.companyRepository.saveAll(companies);
	}

	public void deleteCompany(String id){

		this.companyRepository.deleteById(id);
	}

	public void editCompany(String id,String companyCode,String companyName){
		Optional<Company> company=companyRepository.findById(id);
		if(company.isPresent()){
			Company company1=new Company();
			company1.setCompanyCode(companyCode);
			company1.setCompanyName(companyName);
			companyRepository.save(company1);
		}
	}
}