package study.project.Productmanagement.data.usecases.createProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.ProductRepository;
import study.project.Productmanagement.domain.product.RequestProduct;

@Service
public class CreateProduct {
    @Autowired
    private ProductRepository repository;
    public Product perform (RequestProduct data) {
        Product product = Product.create(data);
        repository.save(product);
        return product;
    }
}
