package com.otters.computerstore.component.bill;

import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public class BillEntity extends NoteEntity {
    public BillEntity(String note) {
        super(note);
    }
}
