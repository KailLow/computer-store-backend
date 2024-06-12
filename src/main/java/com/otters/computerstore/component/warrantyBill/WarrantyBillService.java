package com.otters.computerstore.component.warrantyBill;

import com.otters.computerstore.component.customer.CustomerEntity;
import com.otters.computerstore.component.customer.CustomerRepo;
import com.otters.computerstore.component.customer.CustomerService;
import com.otters.computerstore.component.customer.dto.CustomerDTO;
import com.otters.computerstore.component.envers.AuditEnversInfo;
import com.otters.computerstore.component.envers.AuditEnversInfoRepo;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.product.ProductRepo;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.warrantyBill.dto.WarrantyBillDTO;
import com.otters.computerstore.component.warrantyProduct.WarrantyProductEntity;
import com.otters.computerstore.component.warrantyProduct.WarrantyProductRepo;
import com.otters.computerstore.component.warrantyProduct.WarrantyProductService;
import com.otters.computerstore.component.warrantyProduct.dto.WarrantyProductDTO;
import com.otters.computerstore.mapper.DTOtoEntityMapper;
import com.otters.computerstore.utils.Random;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class WarrantyBillService {
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final WarrantyBillRepo warrantyBillRepo;
    private final WarrantyProductRepo warrantyProductRepo;
    private final WarrantyProductService warrantyProductService;
    private final DTOtoEntityMapper mapper;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    private final CustomerService customerService;

    public Boolean checkWarranty(Date buyDate, Period periodTime){
        if (Period.between(LocalDate.now(), buyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonths() > periodTime.getMonths())
            return false;
        return true;
    }

    public WarrantyBillDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        List<WarrantyProductDTO> warrantyProductDTOS = new ArrayList<>();
        for(int i=0; i<=3;i++){
            warrantyProductDTOS.add(warrantyProductService.random());
        }
        CustomerDTO customerDTO = customerService.random();
        CustomerEntity customerEntity = customerService.postCustomer(customerDTO);
        String customerId = customerEntity.getId();
        return new WarrantyBillDTO(customerId , warrantyProductDTOS);
    }
    public WarrantyBillEntity postWarrantyBill(WarrantyBillDTO dto, StaffEntity staff) {
        dto.setStaffId(staff.getId());
        List<WarrantyProductDTO> warrantyProducts = dto.getWarrantyProducts();
        CustomerEntity customer = customerRepo.findById(dto.getCustomerId()).get();
        WarrantyBillEntity warranty = updateFromDTO(dto, new WarrantyBillEntity());
        warranty.setCustomer(customer);
        warrantyBillRepo.save(warranty);
        for (WarrantyProductDTO t : warrantyProducts) {
            WarrantyProductEntity warrantyProduct = updateFromDTO(t, new WarrantyProductEntity());
            Optional<ProductEntity> product = productRepo.findById(t.getProductId());
            warrantyProduct.setProduct(product.get());
            warrantyProduct.setWarrantyBill(warranty);
            warrantyProductRepo.save(warrantyProduct);
            List<WarrantyProductEntity> warrantyPrts = warranty.getWarrantyProducts();
            warrantyPrts.add(warrantyProduct);
            warranty.setWarrantyProducts(warrantyPrts);
        }
        warrantyBillRepo.save(warranty);
        return warranty;
    }
    private WarrantyBillEntity updateFromDTO(WarrantyBillDTO dto, WarrantyBillEntity entity) {
        mapper.updateWarrantyBillFromDto(dto, entity);
        return entity;
    }

    private WarrantyProductEntity updateFromDTO(WarrantyProductDTO dto, WarrantyProductEntity entity) {
        mapper.updateWarrantyProductFromDto(dto, entity);
        return entity;
    }
    public List<?> getRevisions(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(WarrantyBillEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("warrantyDate"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                WarrantyBillEntity entity = warrantyBillRepo.findById((String) objArray[4]).get();
                List<WarrantyProductEntity> warrantyProducts =  warrantyProductRepo.findByWarrantyBillId(entity.getId());
                entity.setWarrantyProducts(warrantyProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        return audit;
    }


    public List<?> getAll() {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(WarrantyBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("warrantyDate"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                WarrantyBillEntity entity = warrantyBillRepo.findById((String) objArray[4]).get();
                List<WarrantyProductEntity> warrantyProducts =  warrantyProductRepo.findByWarrantyBillId(entity.getId());
                entity.setWarrantyProducts(warrantyProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        return audit;
    }
    public List<?> getAllRevisions(Date start, Date end) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(WarrantyBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("customer_id"))
                .addProjection(AuditEntity.property("warrantyDate"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> audit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                WarrantyBillEntity entity = warrantyBillRepo.findById((String) objArray[4]).get();
                List<WarrantyProductEntity> warrantyProducts =  warrantyProductRepo.findByWarrantyBillId(entity.getId());
                entity.setWarrantyProducts(warrantyProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        List<AuditEnversInfo> auditReturn = new ArrayList<AuditEnversInfo>();
        for(int i=0; i< audit.size();i++){
            if (audit.get(i).getTimestamp() > start.getTime() && audit.get(i).getTimestamp() < end.getTime() ) {
                auditReturn.add(audit.get(i));
            }
        }
        return auditReturn;
    }
}