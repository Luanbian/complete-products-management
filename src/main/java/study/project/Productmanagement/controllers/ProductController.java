package study.project.Productmanagement.controllers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import study.project.Productmanagement.data.usecases.ListProducts.ListProducts;
import study.project.Productmanagement.data.usecases.createProduct.CreateProduct;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.ProductRepository;
import study.project.Productmanagement.domain.product.RequestProduct;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repository;
    private final CreateProduct createProduct;
    private final ListProducts listProducts;

    @Autowired
    public ProductController (
            ProductRepository repository,
            CreateProduct createProduct,
            ListProducts listProducts
    ) {
        this.repository = repository;
        this.createProduct = createProduct;
        this.listProducts = listProducts;
    }

    @GetMapping
    public ResponseEntity getProducts() {
        List<Product> listProduct = listProducts.perform();
        return ResponseEntity.ok(listProduct);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = createProduct.perform(data);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deleteProduct(@RequestParam("id") String id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
