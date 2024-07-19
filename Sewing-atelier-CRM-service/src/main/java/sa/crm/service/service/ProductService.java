package sa.crm.service.service;

import java.util.Optional;

import sa.crm.service.entity.Product;

public interface ProductService {
	
	Iterable<Product> findAllProducts(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
