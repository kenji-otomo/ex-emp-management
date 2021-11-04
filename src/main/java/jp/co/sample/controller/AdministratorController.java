package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService service;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		
		return new InsertAdministratorForm(); 
		
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	
	
	
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		
		Administrator administrator = new Administrator();
		
		BeanUtils.copyProperties(form, administrator);
		
		service.insert(administrator);
		
		return "redirect:toLogin";
	}
	
	
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/login")
	public String login(LoginForm form,Model model) {
		Administrator administrator = service.login(form.getMailAddress(), form.getPassword());
		
		if (administrator == null) {
			
			model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
			return "administrator/login";
			//return "redirect:/";
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/emplyee/showList";
		
	}
	
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
}
