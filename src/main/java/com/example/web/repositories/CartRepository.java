package com.example.web.repositories;

import com.example.web.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional // для удаления товара из корзины. без него по умолчанию только чтение
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByPersonId(int id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_cart WHERE product_id = ?1 AND person_id = ?2", nativeQuery = true)
    void deleteCartById(int id, int personId);
}
