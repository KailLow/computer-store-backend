package com.otters.computerstore.component.importBill.dto;

import com.otters.computerstore.component.importProduct.dto.ImportProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportBillDTO {
    private String id;
    private String staffId;
    private String supplierId;
    private String paymentMethod;
    private List<ImportProductDTO> importProducts = new ArrayList<>();

    public ImportBillDTO(String supplierId, String paymentMethod, List<ImportProductDTO> importProducts) {
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
        this.importProducts = importProducts;
    }

    public ImportBillDTO(String staffId, String supplierId, String paymentMethod) {
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
    }

    public ImportBillDTO(String id, String staffId, String supplierId, String paymentMethod) {
        this.setId(id);
        this.staffId = staffId;
        this.supplierId = supplierId;
        this.paymentMethod = paymentMethod;
    }


}
