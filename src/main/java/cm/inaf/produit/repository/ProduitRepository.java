package cm.inaf.produit.repository;

import cm.inaf.produit.model.ProduitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<ProduitModel, Integer> {
}
