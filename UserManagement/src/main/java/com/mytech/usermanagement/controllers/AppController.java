package com.mytech.usermanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mytech.usermanagement.models.User;

@Controller
public class AppController {

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "index";
	}
	@GetMapping("/403")
	public String viewErrorPage(Model model) {
		return "403";
	}
	@GetMapping("/login")
	public String ViewLoginModel(Model model) {
		User user= new User("admin", "123456");
		model.addAttribute("user",user);
		return "login";
	}
	@GetMapping("/login_success")
	public String ViewLoginSuccessModel(Model model) {
		return "login_success";
	}
	@GetMapping("/login_error")
	public String ViewLoginErrorModel(Model model) {
		return "login_error";
	}

	@GetMapping("/new")
	public String showNewProductForm(Model model) {
		return "new_product";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView("edit_product");

		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		return "redirect:/";

	}

	@PostMapping("/login_success_handler")
	public String loginSuccessHandler() {
		System.out.println("111111111111111:login_success_handler");
		// return "login_success";
		return "index";
	}

}
