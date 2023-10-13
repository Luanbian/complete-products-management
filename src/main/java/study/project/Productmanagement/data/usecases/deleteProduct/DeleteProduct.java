package study.project.Productmanagement.data.usecases.deleteProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.project.Productmanagement.domain.product.Product;
import study.project.Productmanagement.domain.product.ProductRepository;

import java.util.Optional;
@Service
public class DeleteProduct {
    @Autowired
    private ProductRepository repository;

    public String perform (String id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            repository.deleteById(id);
            return "Deletado com sucesso";
        } else {
            return "Id não encontrado";
        }
    }
}
