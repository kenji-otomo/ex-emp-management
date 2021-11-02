package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/sample")
public class sampleController {

	@Autowired
	AdministratorService service ;
	
	@RequestMapping("")
	public String index() {
		
		List<Administrator>list = service.findByMailAddressAndPassword("iga@sample.com", "testtest");
		System.out.println(list);
		
		return "administrator/login";
	}
}
