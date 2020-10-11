package com.example.Skeleton.Services;

import com.example.Skeleton.Dao.Products;
import com.example.Skeleton.Dao.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
