package cm.inaf.produit.service;

import cm.inaf.produit.model.ProduitModel;

import java.util.List;


public interface ProduitService {
    ProduitModel saveProduct(ProduitModel produitModel);
    List<ProduitModel> getAllProduct();
    ProduitModel getProduct(Integer id);
    Boolean deleteProductById(int id);
    ProduitModel updateProduct(ProduitModel produitModel);
}
