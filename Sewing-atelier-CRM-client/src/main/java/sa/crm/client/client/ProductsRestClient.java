package sa.crm.client.client;

import java.util.List;
import java.util.Optional;

import sa.crm.client.entity.Product;

public interface ProductsRestClient {
	
	List<Product> findAllProducts();
	
	Product createProduct(String title, String details);
	
	Optional<Product> findProduct(int productId);
	
	void updateProduct(int productId, String title, String details);
	
	void deleteProduct(int productId);
}
