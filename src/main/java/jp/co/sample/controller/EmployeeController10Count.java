package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee10")
public class EmployeeController10Count {

	@Autowired
	private EmployeeService service;
	
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		
		List<List<Employee>>employeeListParent = new ArrayList<>();
		List<Employee>emListChaild = new ArrayList<>();
		
		List<Employee>employeeList = service.showList();
		
		int count = 0;
		for (int i = 0;i < employeeList.size(); i++) {
			
			for (int j = 0; j < 2; j++) {
				
				emListChaild.add(employeeList.get(j + count));
				count = count + 2;
			}
			
			employeeListParent.add(i,emListChaild);
			emListChaild = new ArrayList<>();
		}
		
		System.out.println(employeeListParent.get(1));
		model.addAttribute("employeeListParent", employeeListParent);
		
		return "employee/list";
	}
	
	
}
