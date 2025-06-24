package cm.inaf.produit.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name= "produit")
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

    public ProduitModel(Integer id_, String productName, String description, Double price, LocalDate dateRegister) {
        this.id_ = id_;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.dateRegister = dateRegister;
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
