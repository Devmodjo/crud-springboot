package cm.inaf.produit.model;

import java.time.LocalDate;

import jakarta.persistence.Transient;
import lombok.Data;


@Data
public class ErrorEntityModel {

    private String message;

    private LocalDate date;

    @Transient
    private String author;

    public ErrorEntityModel() {

    }

    public ErrorEntityModel(String message, LocalDate date, String author) {
        this.message = message;
        this.author = author;
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
