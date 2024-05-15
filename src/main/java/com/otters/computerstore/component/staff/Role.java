package com.otters.computerstore.component.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    STAFF("STAFF"), ADMIN("ADMIN");

    private final String role;
}
