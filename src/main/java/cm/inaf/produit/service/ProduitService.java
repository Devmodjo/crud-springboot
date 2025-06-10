package cm.inaf.produit.service;

import cm.inaf.produit.model.ProduitModel;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    ProduitModel saveProduct(ProduitModel produitModel);
    List<ProduitModel> getAllProduct();
    Optional<ProduitModel> getProduct(Integer id);
    ProduitModel deleteProduct(ProduitModel produitModel);
    ProduitModel updateProduct(ProduitModel produitModel);
}
