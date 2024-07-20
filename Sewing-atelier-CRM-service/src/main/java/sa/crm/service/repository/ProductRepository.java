package sa.crm.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sa.crm.service.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	// http://localhost:8081/catalogue-api/products?filter=%25iPhone%25
	
//	Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);
	
//	@Query(/*name = "",*/ value = "select p from Product p where p.title ilike :filter") // Названия полей класса а не колонок в таблице
//	Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
	
	//2) SQL - вариант кастомных запросов на нативном
//	@Query(value = "select * from catalogue.t_product where c_title ilike :filter", nativeQuery = true)
//	Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
	
	// 3) Использование именованных запросов в сущности
	@Query(name = "Product.findAllByTitleLikeIgnoringCase", nativeQuery = true)
	Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);
	
	

}
