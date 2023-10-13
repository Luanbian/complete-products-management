package study.project.Productmanagement.controllers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import study.project.Productmanagement.data.usecases.deleteProduct.DeleteProduct;
import study.project.Productmanagement.data.usecases.listProducts.ListProducts;
import study.project.Productmanagement.data.usecases.createProduct.CreateProduct;
import study.project.Productmanagement.data.usecases.updateProduct.UpdtProd;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.RequestProduct;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final CreateProduct createProduct;
    private final ListProducts listProducts;
    private final UpdtProd<Object> updateProduct;
    private final DeleteProduct deleteProduct;

    @Autowired
    public ProductController (
            CreateProduct createProduct,
            ListProducts listProducts,
            UpdtProd<Object> updateProduct,
            DeleteProduct deleteProduct
    ) {
        this.createProduct = createProduct;
        this.listProducts = listProducts;
        this.updateProduct = updateProduct;
        this.deleteProduct = deleteProduct;
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
        String delete = deleteProduct.perform(id);
        return ResponseEntity.ok(delete);
    }
}
