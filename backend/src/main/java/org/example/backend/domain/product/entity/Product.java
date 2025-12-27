package org.example.backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.base.BaseEntity;
import org.example.backend.domain.supplier.entity.Supplier;

@Entity
@Table(name="products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String sku;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="supplier_id",nullable = false)
    private Supplier supplier;

    @Column(nullable = false)
    private boolean active=true;
    public Product(String name, String sku, Supplier supplier) {
        this.name = name;
        this.sku = sku;
        this.supplier = supplier;
    }

    public void deactivate() {
        this.active = false;
    }
}
