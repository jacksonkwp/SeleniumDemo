package demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

	private final ItemRepository repo;
	 
	public ViewController(ItemRepository repo) {
		this.repo = repo;
	}

	@ModelAttribute(name = "items")
	public List<Item> items() {
		return repo.selectAll();
	}
	
	@GetMapping
	public String showItemForm() {
		return "view";
	}
}
