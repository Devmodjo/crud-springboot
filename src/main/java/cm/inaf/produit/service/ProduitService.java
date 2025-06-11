package cm.inaf.produit.service;

import cm.inaf.produit.model.ProduitModel;

import java.util.List;
import java.util.Optional;

public interface ProduitService {
    ProduitModel saveProduct(ProduitModel produitModel);
    List<ProduitModel> getAllProduct();
    ProduitModel getProduct(Integer id);
    void deleteProductById(Integer id);
    void deleteAllProduct(ProduitModel produitModel);
    ProduitModel updateProduct(ProduitModel produitModel);
}
