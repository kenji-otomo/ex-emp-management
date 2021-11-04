package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/sample")
public class sampleController {

	@Autowired
	AdministratorService service ;
	
	@RequestMapping("")
	public String index() {
		
		
		return "administrator/login2";
	}
	
	@RequestMapping("/result")
	public String result(String mailAddress,String password) {
		
		Administrator ad = service.login(mailAddress, password);
		System.out.println(ad);
		
		return "administrator/login2";
	}
	
//	@Autowired
//	EmployeeRepository repository;
	
//	@RequestMapping("")
//	public String index() {
//		List<Employee>list = repository.findAll();
//		
//		for (Employee employee : list) {
//			System.out.println(employee);
//		}
		
		
		
//		Employee employee = repository.load(1);
//		System.out.println(employee);
//		
//		employee.setName("山田太郎");
//		repository.update(employee);
//		
//		Employee employee2 = repository.load(1);
//		System.out.println(employee2);
//		
//		
//		return "administrator/login2";
//	}
}
