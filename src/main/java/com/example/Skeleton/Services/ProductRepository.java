package com.example.Skeleton.Services;

import com.example.Skeleton.Component.Products;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
}
