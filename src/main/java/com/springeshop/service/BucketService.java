package com.springeshop.service;

import com.springeshop.data.domain.Bucket;
import com.springeshop.data.domain.Product;
import com.springeshop.data.domain.PurchasedItem;
import com.springeshop.data.domain.User;
import com.springeshop.data.dto.ProductDTO;
import com.springeshop.exceptions.UserNotFoundException;
import com.springeshop.mappers.ProductMapper;
import com.springeshop.repositories.BucketRepository;
import com.springeshop.repositories.ProductRepository;
import com.springeshop.repositories.PurchasedItemRepository;
import com.springeshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchasedItemRepository purchasedItemRepository;
    private final ProductMapper productMapper = ProductMapper.MAPPER;
    public void addItemToBucket(Long productId, String username){
        User user = userRepository.findFirstByName(username);
        if(user != null) {
            Product product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("Product not found"));
            Bucket bucket = user.getBucket();
            if(bucket == null){
                bucket = new Bucket();
                bucket.setUser(user);
                user.setBucket(bucket);
            }
            List<PurchasedItem> purchasedItems = bucket.getPurchasedItems();
            if (purchasedItems == null) {
                purchasedItems = new ArrayList<>();
            }
            PurchasedItem purchasedItem = new PurchasedItem();
            purchasedItem.setBucket(bucket);
            purchasedItem.setProduct(product);
            purchasedItems.add(purchasedItem);
            bucket.setPurchasedItems(purchasedItems);
            bucketRepository.save(bucket);
        }
    }

    public void removeItemFromBucket(Long itemId){
        purchasedItemRepository.deleteById(itemId);
    }

    public List<ProductDTO> getAll(String username){
        User user = userRepository.findFirstByName(username);
        if(user != null) {
            Bucket bucket = user.getBucket();
            if(bucket != null) {
                List<PurchasedItem> purchasedItems = bucket.getPurchasedItems();
                return productMapper.toProductDTOList(
                        purchasedItems.stream()
                                .map(PurchasedItem::getProduct)
                                .collect(Collectors.toList()));
            }
        }
        return null;
    }
}
