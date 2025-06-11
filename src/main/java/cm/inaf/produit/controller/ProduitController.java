package cm.inaf.produit.controller;


import cm.inaf.produit.exception.ResourceNotFoundException;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.service.ProduitService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:5173")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping("/save")
    public ProduitModel createProduct(@RequestBody ProduitModel produitModel){
        return produitService.saveProduct(produitModel);
    }

    @GetMapping("/get")
    public List<ProduitModel> getAllProduct(){
        return produitService.getAllProduct();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProduitModel> getProductById(@PathVariable Integer id){
        ProduitModel produitModel = Optional.ofNullable(produitService.getProduct(id)).orElseThrow(() -> new ResourceNotFoundException("Ce produit n'existe pas"));
        return  ResponseEntity.ok(produitModel);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitModel> updateProduct(@PathVariable Integer id, @RequestBody ProduitModel produitModel){
        ProduitModel pm = Optional.ofNullable(produitService.getProduct(id)).orElseThrow(() -> new ResourceNotFoundException("Ce produit n'existe pas"));
        return ResponseEntity.ok(produitService.updateProduct(pm));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id){
        Boolean p = this.produitService.deleteProductById(id);
        if(p){
            return ResponseEntity.status(HttpStatus.OK).body(p);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p);
    }

}
