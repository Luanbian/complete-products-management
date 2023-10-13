package study.project.Productmanagement.data.usecases.ListProducts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.ProductRepository;

import java.util.List;
@Service
public class ListProducts {
    @Autowired
    private ProductRepository repository;

    public List<Product> perform () {
        var products = repository.findAll();
        return products;
    }
}
