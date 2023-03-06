package demo;


import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {

	private final ItemRepository repo;
	 
	public DeleteController(ItemRepository repo) {
		this.repo = repo;
	}

	@ModelAttribute(name = "item")
	public Item item() {
		return new Item();
	}
	
	@GetMapping
	public String showItemForm() {
		return "delete";
	}
	
	@PostMapping
	public String deleteItem(@Valid Item item, Errors errors) {
		
		if (errors.hasErrors()) {
			return "delete";
		}
		
		//find item to delete
		Item target = repo.selectByName(item.getName());
		if (target == null) {
			return "redirect:/delete";
		}
		
		//delete it
		System.out.println("Deleting: " + target.getName());
		repo.delete(target);
		
		return "redirect:/delete";
	}
}
