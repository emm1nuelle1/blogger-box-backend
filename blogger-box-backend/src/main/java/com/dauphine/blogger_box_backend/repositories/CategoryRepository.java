package com.dauphine.blogger_box_backend.repositories;

import com.dauphine.blogger_box_backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("""
        SELECT c FROM Category c 
        WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))
    """)
    List<Category> findAllLikeName(@Param("name") String name);
}
