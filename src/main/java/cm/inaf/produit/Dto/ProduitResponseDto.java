package cm.inaf.produit.Dto;

import cm.inaf.produit.model.ProduitModel;

import java.time.LocalDate;

public record ProduitResponseDto(
        Integer id_,
        String produtName,
        String description,
        Double price,
        LocalDate dateRegister
) { }
