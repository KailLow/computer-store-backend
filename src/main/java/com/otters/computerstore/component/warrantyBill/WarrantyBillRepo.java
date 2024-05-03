package com.otters.computerstore.component.warrantyBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyBillRepo extends JpaRepository<WarrantyBillEntity, String> {
}
