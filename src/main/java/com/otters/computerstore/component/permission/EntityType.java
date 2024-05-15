package com.otters.computerstore.component.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    PRODUCT("PRODUCT"),
    CATEGORY("CATEGORY"),
    CUSTOMER("CUSTOMER"),
    SUPPLIER("SUPPLIER"),
    STAFF("STAFF"),
    IMPORT_BILL("IMPORT_BILL"),
    SALE_BILL("SALE_BILL"),
    WARRANTY_BILL("WARRANTY_BILL"),
    DASHBOARD("DASHBOARD");

    private final String entityType;
}
