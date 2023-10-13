package study.project.Productmanagement.controllers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import study.project.Productmanagement.data.usecases.ListProducts.ListProducts;
import study.project.Productmanagement.data.usecases.createProduct.CreateProduct;
import study.project.Productmanagement.data.usecases.updateProduct.UpdateProduct;
import study.project.Productmanagement.data.usecases.updateProduct.UpdtProd;
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
    private final UpdtProd<Object> updateProduct;

    @Autowired
    public ProductController (
            ProductRepository repository,
            CreateProduct createProduct,
            ListProducts listProducts,
            UpdtProd<Object> updateProduct
    ) {
        this.repository = repository;
        this.createProduct = createProduct;
        this.listProducts = listProducts;
        this.updateProduct = updateProduct;
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
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid RequestProduct data) {
        Object update = updateProduct.perform(data);
        return ResponseEntity.ok(update);
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
