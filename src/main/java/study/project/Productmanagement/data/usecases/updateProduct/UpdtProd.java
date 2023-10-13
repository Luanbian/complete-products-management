package study.project.Productmanagement.data.usecases.updateProduct;

import study.project.Productmanagement.domain.product.RequestProduct;

public interface UpdtProd<T> {
    T perform(RequestProduct data);
}
