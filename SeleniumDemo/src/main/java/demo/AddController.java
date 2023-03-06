package demo;


import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class AddController {

	private final ItemRepository repo;
	 
	public AddController(ItemRepository repo) {
		this.repo = repo;
	}

	@ModelAttribute(name = "item")
	public Item item() {
		return new Item();
	}
	
	@GetMapping
	public String showItemForm() {
		return "add";
	}
	
	@PostMapping
	public String addItem(@Valid Item item, Errors errors) {
		
		if (errors.hasErrors()) {
			return "add";
		}
		
		repo.save(item);
		return "redirect:/add";
	}
}
