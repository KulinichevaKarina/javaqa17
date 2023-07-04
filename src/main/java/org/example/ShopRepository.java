package org.example;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        if (findById(product.getId()) == null) {
            products = addToArray(products, product);
            return;
        }
        throw new AlreadyExistsException("Element with id: " + product.getId() + " not found");
    }

    public Product[] findAll() {
        return products;
    }

    public void remove(int id) {
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int productId) {
        for (Product product : findAll()) {
            if (productId == product.getId()) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) != null) {
            remove(id);
            return;
        }
        throw new NotFoundException("Element with id: " + id + " not found");
    }
}
