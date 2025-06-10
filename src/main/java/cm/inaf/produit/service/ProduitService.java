package cm.inaf.produit.service;

import cm.inaf.produit.model.ProduitModel;

import java.util.List;

public interface ProduitService {
    ProduitModel saveProduct(ProduitModel produitModel);
    List<ProduitModel> getAllProduct(ProduitModel produitModel);
    ProduitModel getProduct(ProduitModel produitModel);
    ProduitModel deleteProduct(ProduitModel produitModel);
    ProduitModel updateProduct(ProduitModel produitModel);
}
