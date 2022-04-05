package com.meedra.eynsuree.repository;

import com.meedra.eynsuree.model.Customer;
import com.meedra.eynsuree.model.InsuredItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuredItemRepository extends JpaRepository<InsuredItem, Long> {

    Optional<List<InsuredItem>> findByCustomer(Customer customer);
    Optional<InsuredItem> findByUid(UUID uuid);

}