package com.otters.computerstore.mapper;

import com.otters.computerstore.component.category.CategoryEntity;
import com.otters.computerstore.component.category.dto.CategoryDTO;
import com.otters.computerstore.component.customer.CustomerEntity;
import com.otters.computerstore.component.customer.dto.CustomerDTO;
import com.otters.computerstore.component.importBill.ImportBillEntity;
import com.otters.computerstore.component.importBill.dto.ImportBillDTO;
import com.otters.computerstore.component.importProduct.ImportProductEntity;
import com.otters.computerstore.component.importProduct.dto.ImportProductDTO;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.product.dto.ProductDTO;
import com.otters.computerstore.component.saleBill.SaleBillEntity;
import com.otters.computerstore.component.saleBill.dto.SaleBillDTO;
import com.otters.computerstore.component.saleProduct.SaleProductEntity;
import com.otters.computerstore.component.saleProduct.dto.SaleProductDTO;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.staff.dto.StaffDTO;
import com.otters.computerstore.component.supplier.SupplierEntity;
import com.otters.computerstore.component.supplier.dto.SupplierDTO;
import com.otters.computerstore.component.warrantyBill.WarrantyBillEntity;
import com.otters.computerstore.component.warrantyBill.dto.WarrantyBillDTO;
import com.otters.computerstore.component.warrantyProduct.WarrantyProductEntity;
import com.otters.computerstore.component.warrantyProduct.dto.WarrantyProductDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DTOtoEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryDTO dto, @MappingTarget CategoryEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDTO dto, @MappingTarget ProductEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStaffFromDto(StaffDTO dto, @MappingTarget StaffEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplierFromDto(SupplierDTO dto, @MappingTarget SupplierEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerDTO dto, @MappingTarget CustomerEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateImportBillFromDto(ImportBillDTO dto, @MappingTarget ImportBillEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateImportProductFromDto(ImportProductDTO dto, @MappingTarget ImportProductEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSaleBillFromDto(SaleBillDTO dto, @MappingTarget SaleBillEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSaleProductFromDto(SaleProductDTO dto, @MappingTarget SaleProductEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWarrantyBillFromDto(WarrantyBillDTO dto, @MappingTarget WarrantyBillEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWarrantyProductFromDto(WarrantyProductDTO dto, @MappingTarget WarrantyProductEntity entity);
    default List<SupplierEntity> map(List<String> value) {
        return value.stream().map(v -> new SupplierEntity()).toList();
    }
    default List<CustomerEntity> mapp(List<String> value) {
        return value.stream().map(v -> new CustomerEntity()).toList();
    }
}