package com.stockMarket.controller;


import com.stockMarket.entities.Stock;
import com.stockMarket.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("stocks")
public class StockController {

	@Autowired
	private StockService stockService;


	@GetMapping("getAllStocks")
//	@Cacheable(value = "stocks")
	public List<Stock> getAllAirlines() {
		return this.stockService.getStocks();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("getStock/{companyCode}")

	public List<Stock> getStock(@PathVariable("companyCode") String companyCode) {
		List<Stock> stockList= this.stockService.getStocks();
		List<Stock> stockCompanyList=stockList.stream().filter(x->x.getCompanyCode().equalsIgnoreCase(companyCode)).collect(Collectors.toList());
		return stockCompanyList;
	}

	@PostMapping("saveStocks")
//	@Cacheable(value = "stocks")
	public void saveStocks(@RequestBody List<Stock> stocks) {
		 this.stockService.saveStocks(stocks);
	}

	@PostMapping("deleteStocks/{stockId}")
	public void deleteStocks(@PathVariable("stockId") String stockId) {
		this.stockService.deleteStocks(stockId);
	}

	@PutMapping("editStocks/{stockId}/{companyCode}")
	public void editStocks(@PathVariable("stockId") String stockId,
						   @PathVariable("companyCode") String companyCode) {
		this.stockService.editStocks(stockId,companyCode);
	}

}
