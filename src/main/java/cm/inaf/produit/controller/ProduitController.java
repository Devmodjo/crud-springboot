package cm.inaf.produit.controller;


import cm.inaf.produit.exception.ResourceNotFoundException;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.service.ProduitService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:5173")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/save")
    public ProduitModel createProduct(@RequestBody ProduitModel produitModel){
        return produitService.saveProduct(produitModel);
    }

    @GetMapping("/get")
    public List<ProduitModel> getAllProduct(){
        return  produitService.getAllProduct();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProduitModel> getProductById(@PathVariable Integer id){
        ProduitModel produitModel = Optional.ofNullable(produitService.getProduct(id)).orElseThrow(
                () -> new ResourceNotFoundException("Ce produit n'existe pas")
        );
        return  ResponseEntity.ok(produitModel);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitModel> updateProduct(@PathVariable Integer id, @RequestBody ProduitModel produitModel){
        ProduitModel pm = Optional.ofNullable(produitService.getProduct(id)).orElseThrow(
                ()->new ResourceNotFoundException("Ce produit n'existe pas")
        );
        pm.setProductName(produitModel.getProductName());
        pm.setDescription(produitModel.getDescription());
        pm.setPrice(produitModel.getPrice());

        return ResponseEntity.ok(produitService.updateProduct(pm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id){
        ProduitModel produitModel = Optional.ofNullable(produitService.getProduct(id))
                .orElseThrow(() -> new ResourceNotFoundException("Ce produit n'existe pas !"));

        produitService.deleteAllProduct(produitModel);

        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("Deleted !", Boolean.TRUE);

        return  ResponseEntity.ok(reponse);
    }

}
