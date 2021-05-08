package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;







@Controller
public class contrloler {
	@Autowired
	CustomersRepo repo;
	
	 
	 //view form
	 @RequestMapping("/")
		public String form() {
			return "form";
		}
	 
	 //save to database and again view form
	 @RequestMapping("/details")
		public String details(Customers customers) {
		 repo.save(customers);
			return "form";
		}
	 
	 @RequestMapping("/getdetails")
		public String getDetails() {
		 
			return "viewcustomers";
		}
	 
	 /*
	@GetMapping("form")
	public String form() {
		return "form";
	}*/
	
	@PostMapping("/getdetails")
	public ModelAndView details (@RequestParam int cid) {
		
		  
		ModelAndView mv = new ModelAndView("retrive");
		
		Customers customers = repo.findById(cid).orElse(null);
		mv.addObject(customers); 
		
		
		return mv;
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getCustomers() {
		return repo.findAll();
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public String getCustomers2(@PathVariable("cid") int cid) {
		return repo.findById(cid).toString();
	}
	
	@PostMapping("customers")
	public Customers getCustomers3(@RequestBody Customers customers){
		repo.save(customers);
		return customers;
	}
	
	@DeleteMapping("customers/{cid}")
	public Customers delCustomers4(@PathVariable("cid") int cid){
		
		Customers cust = repo.getOne(cid);
		repo.delete(cust);
		return cust;
	}
	
	@PutMapping(path = "/customers", consumes = {"application/json"})
	public Customers getCustomers4(@RequestBody Customers customers ){
		
		repo.save(customers);
		
		return customers;
	}
	
	


}
