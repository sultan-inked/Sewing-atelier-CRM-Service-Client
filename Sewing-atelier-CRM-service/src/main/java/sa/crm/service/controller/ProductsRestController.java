package sa.crm.service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sa.crm.service.controller.payload.NewProductPayload;
import sa.crm.service.entity.Product;
import sa.crm.service.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {
	
	private final ProductService productService;
	
	@GetMapping
	public Iterable<Product> findProducts(@RequestParam(name = "filter", required = false) String filter) {
		
		return this.productService.findAllProducts(filter);
	}
	
	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody NewProductPayload newProductPayload,
										   BindingResult bindingResult,
										   UriComponentsBuilder uriComponentsBuilder) throws BindException {
		if(bindingResult.hasErrors()) {
			if(bindingResult instanceof BindException bindException) {
				throw bindException;
			} else {
				throw new BindException(bindingResult);
			}
		} else {
			Product product = this.productService.createProduct(newProductPayload.title(), newProductPayload.details());
			return ResponseEntity
					.created(uriComponentsBuilder
							.replacePath("/catalogue-api/products/{productId}")
							.build(Map.of("productId", product.getId())))
					.body(product);
		}
	}
}
