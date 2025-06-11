package cm.inaf.produit.service.serviceImplement;

import cm.inaf.produit.exception.ResourceNotFoundException;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.repository.ProduitRepository;
import cm.inaf.produit.service.ProduitService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class ProduitImpl implements ProduitService {

    public final ProduitRepository produitRepository;

    public ProduitImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitModel saveProduct(ProduitModel produitModel) {
        return produitRepository.save(produitModel);
    }

    @Override
    public List<ProduitModel> getAllProduct() {
        return produitRepository.findAll();
    }

    @Override
    public ProduitModel getProduct(Integer id) {
        return produitRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("ce Produit n'exite pas !")
        );
    }

    @Override
    public Boolean deleteProductById(int id) {
        Optional<ProduitModel> foundProduct = produitRepository.findById(id);
        if(foundProduct.isPresent()){
            produitRepository.delete(foundProduct.get());
            return true;
        }
        return false;
    }


    @Override
    public ProduitModel updateProduct(ProduitModel produitModel) {
        return produitRepository.save(produitModel);
    }
}
