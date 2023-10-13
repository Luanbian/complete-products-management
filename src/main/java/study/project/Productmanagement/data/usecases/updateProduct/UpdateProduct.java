package study.project.Productmanagement.data.usecases.updateProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.ProductRepository;
import study.project.Productmanagement.domain.product.RequestProduct;

import java.util.Optional;

@Service
public class UpdateProduct implements UpdtProd<Object> {
    @Autowired
    private ProductRepository repository;

    public Object perform (RequestProduct data) {
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return product;
        } else {
            return "Id não encontrado";
        }
    }

}
