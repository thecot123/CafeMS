package com.mytech.usermanagement.controllers;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "index";
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
