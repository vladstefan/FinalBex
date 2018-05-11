package com.db.bexlibrary.BexLibrary.repositories;

import com.db.bexlibrary.BexLibrary.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category,Long> {

    List<Category> findCategoryByCategoryName(String name);
}
