package cm.inaf.produit.repository;

import cm.inaf.produit.Dto.ProduitRequestDto;

import cm.inaf.produit.model.ProduitModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRespositoryIntegrationTest {

    @Autowired
    private ProduitRepository produitRepository;

    private ProduitModel produitModel;

    private ProduitRequestDto produitRequestDto;

    private final int productid = 3;

    @BeforeEach()
    void initData() {
        produitModel = new ProduitModel(productid, "Huile d'arachide", "huile mayor sans cholesterol", 1200.0, LocalDate.now());
        produitRequestDto = new ProduitRequestDto("Huile d'arachide", "huile mayor sans cholesterol", 1200.0, LocalDate.now());
    }

    @Test
    void saveProduct__repository() {
        Optional<ProduitModel> res = Optional.of(produitRepository.save(produitModel));
        assertNotNull(res);
        ProduitModel pm = res.get();
        assertEquals(pm.getProductName(), produitRequestDto.produtName());
        assertEquals(pm.getDescription(), produitRequestDto.description());
    }

    @Test
    void findByIdProduct__repository() {
        Optional<ProduitModel> res = produitRepository.findById(productid);

        if(res.isPresent()){
            ProduitModel pm = res.get();
            assertNotNull(pm);
            assertEquals(pm.getPrice(), produitRequestDto.price());
            assertEquals(pm.getDescription(), produitRequestDto.description());

        }

    }

    @Test
    void findAllProduct__repository(){
        List<ProduitModel> res = produitRepository.findAll();
        assertNotNull(res);
        assertEquals(res.get(0).getPrice(), produitRequestDto.price());
        assertEquals(res.get(0).getProductName(),produitRequestDto.produtName());
    }

    @Test
    void deleteProduct__repositoy(){
        Optional<ProduitModel> found = produitRepository.findById(productid);
        if (found.isPresent()){
            ProduitModel pm = found.get();
            assertNotNull(pm);
            produitRepository.deleteById(productid);
        }
    }

}
