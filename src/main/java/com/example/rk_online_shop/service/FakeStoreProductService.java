package com.example.rk_online_shop.service;

import com.example.rk_online_shop.dto.FakeStoreProductDto;
import com.example.rk_online_shop.exceptions.ProductNotFoundException;
import com.example.rk_online_shop.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService  implements ProductService{
    private final RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto  fkdto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                  FakeStoreProductDto.class
                );
        if(fkdto == null){
            throw new ProductNotFoundException("Product not found " + "with id " + productId);
        }
        return fkdto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products"; // Replace with actual API endpoint if needed
        FakeStoreProductDto[] fklist = restTemplate.getForObject(url, FakeStoreProductDto[].class);
        return Arrays.stream(fklist).map(FakeStoreProductDto::toProduct).toList();
    }

    @Override
    public Product createProduct(Product p) {
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setTitle(p.getTitle());
        fs.setCategory(p.getCategory().getTitle());
        fs.setImage(p.getImageUrl());
        fs.setDescription(p.getDescription());
        fs.setPrice(p.getPrice());

        FakeStoreProductDto resp = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fs,
                FakeStoreProductDto.class
                );
        assert resp != null;
        return resp.toProduct();
    }

    @Override
    public Product updateProduct(Long productId, Product p) {
        FakeStoreProductDto fs1 = new FakeStoreProductDto();
        fs1.setId(p.getId());
        fs1.setTitle(p.getTitle());
        fs1.setCategory(p.getCategory().getTitle());
        fs1.setImage(p.getImageUrl());
        fs1.setDescription(p.getDescription());
        fs1.setPrice(p.getPrice());
        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                fs1
        );
        return fs1.toProduct();
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto  fkdto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        if(fkdto == null){
            throw new ProductNotFoundException("Product not found " + "with id " + productId);
        }
        else{
            restTemplate.delete("https://fakestoreapi.com/products/" + productId);
            return fkdto.toProduct();
        }
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        String url = "https://fakestoreapi.com/products/category/" + category;
        FakeStoreProductDto[] fklist = restTemplate.getForObject(url, FakeStoreProductDto[].class);
        return Arrays.stream(fklist).map(FakeStoreProductDto::toProduct).toList();    }

    @Override
    public List getAllCategory() {
        String url = "https://fakestoreapi.com/products/categories";
        return restTemplate.getForObject(url, List.class);
    }
}
