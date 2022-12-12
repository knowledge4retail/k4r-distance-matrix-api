package org.knowledge4retail.ext.distancematrix.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="distancematrix")
public class DistanceMatrix {

    @Id
    @Column(name="store_id")
    private Integer storeId;

    @Column(name="matrix")
    private String matrix;
}
