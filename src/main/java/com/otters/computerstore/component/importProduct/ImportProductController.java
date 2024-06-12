package com.otters.computerstore.component.importProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path ="import_product")
public class ImportProductController {
    private final ImportProductService importProductService;

    @GetMapping("{id}")
    public ImportProductEntity getImportProduct(@PathVariable String id) {
        return importProductService.getImportProduct(id);
    }

}
