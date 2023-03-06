package demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeleniumDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumDemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(ItemRepository repo) {
		return new CommandLineRunner() {
				@Override
				public void run(String... args) throws Exception {
					repo.deleteAll();
					
					//insert sample data
					Item item1 = new Item();
					item1.setName("Apple Tree");
					repo.save(item1);
					Item item2 = new Item();
					item2.setName("Laptop");
					repo.save(item2);
					Item item3 = new Item();
					item3.setName("Surfboard");
					repo.save(item3);
					Item item4 = new Item();
					item4.setName("Rollercoaster");
					repo.save(item4);
				}
			};
	}
}
