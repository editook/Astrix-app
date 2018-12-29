package com.software.program.astrixsa.model;

import java.util.List;

public interface CategoryI {
    void addProduct(ProductI secction);
    List<ProductI> getProducts();
    ProductI getProduct(int index);
}
