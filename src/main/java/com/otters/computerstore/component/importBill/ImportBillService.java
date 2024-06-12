package com.otters.computerstore.component.importBill;

import com.otters.computerstore.component.envers.AuditEnversInfo;
import com.otters.computerstore.component.envers.AuditEnversInfoRepo;
import com.otters.computerstore.component.importBill.dto.ImportBillDTO;
import com.otters.computerstore.component.importProduct.ImportProductEntity;
import com.otters.computerstore.component.importProduct.ImportProductRepo;
import com.otters.computerstore.component.importProduct.ImportProductService;
import com.otters.computerstore.component.importProduct.dto.ImportProductDTO;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.component.product.ProductRepo;
import com.otters.computerstore.component.staff.StaffEntity;
import com.otters.computerstore.component.supplier.SupplierEntity;
import com.otters.computerstore.component.supplier.SupplierService;
import com.otters.computerstore.component.supplier.dto.SupplierDTO;
import com.otters.computerstore.mapper.DTOtoEntityMapper;
import org.springframework.stereotype.Service;
import com.otters.computerstore.utils.Random;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportBillService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final ImportBillRepo importBillRepo;
    private final ProductRepo productRepo;
    private final DTOtoEntityMapper mapper;
    private final ImportProductRepo importProductRepo;
    private final ImportProductService importProductService;
    private final SupplierService supplierService;



    public ImportBillEntity getImportBill(String importBillId) {
        Optional<ImportBillEntity> importBill = importBillRepo.findById(importBillId);
        if (importBill.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Import bill not found").toString());
        }
        return importBill.get();
    }

    public ImportBillDTO random() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        List<ImportProductDTO> importProductDTOS = new ArrayList<>();
        for(int i=0; i<=3;i++){
            importProductDTOS.add(importProductService.random());
        }
        SupplierDTO supplierDTO = supplierService.random();
        SupplierEntity supplierEntity = supplierService.add(supplierDTO);
        String supplierId = supplierEntity.getId();
        String paymentMethod = Random.random(10, characters);
        return new ImportBillDTO(supplierId, paymentMethod, importProductDTOS);
    }
    public ImportBillEntity postImportBill(ImportBillDTO importBillDTO, StaffEntity staff) {
        importBillDTO.setStaffId(staff.getId());
        List<ImportProductDTO> importPrts = importBillDTO.getImportProducts();
        ImportBillEntity impot = updateFromDTO(importBillDTO, new ImportBillEntity());
        importBillRepo.save(impot);
        for (ImportProductDTO t : importPrts){
            ImportProductEntity impotPrt = updateFromDTO(t, new ImportProductEntity());
            Optional<ProductEntity> product = productRepo.findById(t.getProductId());
            impotPrt.setProduct(product.get());
            impotPrt.setImportBill(impot);
            importProductRepo.save(impotPrt);
            List<ImportProductEntity> importProducts = impot.getImportProducts();
            importProducts.add(impotPrt);
            impot.setImportProducts(importProducts);
        }
        importBillRepo.save(impot);
        return impot;
    }
    private ImportBillEntity updateFromDTO(ImportBillDTO importBillDTO, ImportBillEntity impot) {
        mapper.updateImportBillFromDto(importBillDTO, impot);
        return impot;
    }
    private ImportProductEntity updateFromDTO(ImportProductDTO dto, ImportProductEntity entity) {
        mapper.updateImportProductFromDto(dto, entity);
        return entity;
    }
    @Transactional
    public List<?> getRevisions(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(ImportBillEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("supplierId"))
                .addProjection(AuditEntity.property("paymentMethod"))
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
                ImportBillEntity entity = importBillRepo.findById((String) objArray[4]).get();
                List<ImportProductEntity> importProducts = importProductRepo.findByImportBillId(entity.getId());
                entity.setImportProducts(importProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return audit;
    }
    @Transactional
    public List<?> getAll() {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(ImportBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("supplierId"))
                .addProjection(AuditEntity.property("paymentMethod"))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        Map<String, AuditEnversInfo> audit = new HashMap<>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[0]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                ImportBillEntity entity = importBillRepo.findById((String) objArray[4]).get();
                List<ImportProductEntity> importProducts = importProductRepo.findByImportBillId(entity.getId());
                entity.setImportProducts(importProducts);
                auditEnversInfo.setRevision(entity);
                audit.put(entity.getId(), auditEnversInfo);
            }
        }
        entityManager.close();
        return Arrays.asList(audit.values().toArray());
    }
    @Transactional
    public List<?> getAllRevisions(Date start, Date end) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(ImportBillEntity.class, true, true)
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.property("staffId"))
                .addProjection(AuditEntity.property("supplierId"))
                .addProjection(AuditEntity.property("paymentMethod"))
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
//                ImportBillEntity entity = new ImportBillEntity((String) objArray[1],  (String) objArray[2], (String) objArray[3]);
                ImportBillEntity entity = importBillRepo.findById((String) objArray[4]).get();
                List<ImportProductEntity> importProducts = importProductRepo.findByImportBillId(entity.getId());
                entity.setImportProducts(importProducts);
                auditEnversInfo.setRevision(entity);
                audit.add(auditEnversInfo);
            }
        }
        Map<String, AuditEnversInfo> auditReturn = new HashMap<>();
        for(int i=0; i< audit.size();i++){
            if (audit.get(i).getTimestamp() > start.getTime() && audit.get(i).getTimestamp() < end.getTime() ) {
                ImportBillEntity importBillEntity = (ImportBillEntity) audit.get(i).getRevision();
                auditReturn.put(importBillEntity.getId(), audit.get(i));
            }
        }
        entityManager.close();
        return Arrays.asList(auditReturn.values().toArray());
    }
}