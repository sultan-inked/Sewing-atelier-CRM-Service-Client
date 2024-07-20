package sa.crm.service.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sa.crm.service.entity.Product;
import sa.crm.service.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
	
	private final ProductRepository productRepository;

	@Override
	public Iterable<Product> findAllProducts(String filter) {
		if(filter != null && !filter.isBlank()) {
			return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
		} else {
			return this.productRepository.findAll();
		}
	}

	@Override
	@Transactional
	public Product createProduct(String title, String details) {
		Product product = this.productRepository.save(new Product(null, title, details));
		
		return product;
	}

	@Override
	public Optional<Product> findProduct(int productId) {
		
		return this.productRepository.findById(productId);
	}

	@Override
	@Transactional
	public void updateProduct(Integer id, String title, String details) {
		this.productRepository.findById(id)
				.ifPresentOrElse(product -> {
					product.setTitle(title);
					product.setDetails(details);
					
					// this.productRepository.save(product); // Так можно сохранять без @Transactional
				}, () -> {
					throw new NoSuchElementException();
				});
	}

	@Override
	@Transactional
	public void deleteProduct(Integer id) {
		this.productRepository.deleteById(id);
		
	}
	
	
}
