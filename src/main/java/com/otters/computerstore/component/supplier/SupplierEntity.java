package com.otters.computerstore.component.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.computerstore.component.product.ProductEntity;
import com.otters.computerstore.entity.NoteEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class SupplierEntity extends NoteEntity {
    private String name;
    private String phone;
    private String email;
    private String address;

    @NotAudited
    @JsonIgnoreProperties(value = {"suppliers"})
    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private List<ProductEntity> products;
}
