package com.otters.computerstore.component.saleBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleBillRepo extends JpaRepository<SaleBillEntity, String> {
}
