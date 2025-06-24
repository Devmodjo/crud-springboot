package cm.inaf.produit.Dto;

import java.time.LocalDate;

public record ProduitRequestDto(
        String produtName,
        String description,
        Double price,
        LocalDate dateRegister
) { }
