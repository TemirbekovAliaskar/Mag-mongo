package com.example.web.repositories;

import com.example.web.models.Order;
import com.example.web.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);
}
