package org.example.backend.domain.supplier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.base.BaseEntity;

@Entity
@Table(name = "suppliers")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supplier extends BaseEntity {
    public Supplier(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private boolean active = true;


    public void deactivate() {
        this.active = false;
    }
}
