package cm.inaf.produit.service;

import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.Dto.ProduitResponseDto;


import java.util.List;


public interface ProduitService {
    ProduitResponseDto saveProduct(ProduitRequestDto produitModelRequestDto);

    List<ProduitResponseDto> getAllProduct();

    ProduitResponseDto getProduct(Integer id);

    Boolean deleteProductById(int id);

    ProduitResponseDto updateProduct(int id, ProduitRequestDto produitRequestDtoDto);
}
