package cm.inaf.produit.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name= "produit")
@AllArgsConstructor
@NoArgsConstructor
public class ProduitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_;

    @Column(name= "PRODUCT_NAME")
    private String productName;

    @Column(name= "DESCRIPTION")
    private String description;

    @Column(name= "PRICE")
    private Double price;

    @Column(name= "dateRegister")
    private LocalDate dateRegister;

    public ProduitModel() {
    }

    public ProduitModel(LocalDate dateRegister, Double price, String description, String productName) {
        this.dateRegister = dateRegister;
        this.price = price;
        this.description = description;
        this.productName = productName;
    }

    public Integer getId_() {
        return id_;
    }

    public void setId_(Integer id_) {
        this.id_ = id_;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    @Override
    public String toString() {
        return "ProduitModel{" +
                "id_=" + id_ +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateRegister=" + dateRegister +
                '}';
    }
}
