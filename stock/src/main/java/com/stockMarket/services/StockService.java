package com.stockMarket.services;

import com.stockMarket.entities.Stock;
import com.stockMarket.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public List<Stock> getStocks(){
		return stockRepository.findAll();
	}

	public void saveStocks(List<Stock> stocks){
		for(Stock stock : stocks)
			stock.setDate(new Date());
		this.stockRepository.saveAll(stocks);
	}

	public void deleteStocks(String id){

		this.stockRepository.deleteById(id);
	}

	public void editStocks(String id,String companyCode){
		Optional<Stock> stock=stockRepository.findById(id);
		if(stock.isPresent()){
			Stock stock1=new Stock();
			stock1.setCompanyCode(companyCode);
			stockRepository.save(stock1);
		}
	}
}