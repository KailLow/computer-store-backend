package com.otters.computerstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class NoteEntity extends BaseEntity{
    @Column(name = "note")
    private String note;

    public NoteEntity(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "note='" + note + '\'' +
                '}';
    }
}
