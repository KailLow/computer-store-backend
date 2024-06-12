package com.otters.computerstore.component.envers;

import com.otters.computerstore.component.importBill.ImportBillService;
import com.otters.computerstore.component.saleBill.SaleBillService;
import com.otters.computerstore.component.warrantyBill.WarrantyBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("enver")
@RequiredArgsConstructor
@RestController
public class AuditEnverInfoController {
    private final ImportBillService importBillService;
    private final WarrantyBillService warrantyBillService;
    private final SaleBillService saleBillService;
    private final AuditEnverInfoService auditEnverInfoService;
    @GetMapping("history")
    public ResponseEntity<?> getHistoryByStaff(@RequestParam String username) throws ClassNotFoundException {
        return ResponseEntity.ok(auditEnverInfoService.view(auditEnverInfoService.getRecord(username)));
    }

    @PostMapping
    public void random() {
        auditEnverInfoService.randomData();
    }
}