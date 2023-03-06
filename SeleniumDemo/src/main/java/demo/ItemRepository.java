package demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends CrudRepository<Item, Long> {
	
	@Query("SELECT i FROM Item i ORDER BY createdAt ASC")
	List<Item> selectAll();
	
	@Query("SELECT i FROM Item i WHERE i.name=?#{#name} ORDER BY createdAt DESC LIMIT 1")
	Item selectByName(@Param("name")String name);
}
