package com.mk.ecommerce_springboot.service;

import com.mk.ecommerce_springboot.model.Product;
import com.mk.ecommerce_springboot.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        repo.save(product);
        return product;
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) {
        if(!repo.existsById(id)){
            return null;
        }
        try {
        return addProduct(product,imageFile);
        }catch (Exception e){
            return null;
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
