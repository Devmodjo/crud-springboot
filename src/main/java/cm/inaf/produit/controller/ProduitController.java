package cm.inaf.produit.controller;


import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.Dto.ProduitResponseDto;
import cm.inaf.produit.exception.ResourceNotFoundException;
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
    public ProduitResponseDto createProduct(@RequestBody ProduitRequestDto produitResquestDto){
        return produitService.saveProduct(produitResquestDto);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProduitResponseDto>> getAllProduct(){
        return ResponseEntity.ok(produitService.getAllProduct());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProduitResponseDto> getProductById(@PathVariable Integer id){
        ProduitResponseDto found = Optional.ofNullable(produitService.getProduct(id)).orElseThrow(() -> new ResourceNotFoundException("Ce produit n'existe pas"));
        return  ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProduitResponseDto> updateProduct(@PathVariable Integer id, @RequestBody ProduitRequestDto produitModel){
        ProduitResponseDto pm = Optional.ofNullable(produitService.updateProduct(id, produitModel)).orElseThrow(() -> new ResourceNotFoundException("Ce produit n'existe pas"));
        if(pm != null){
            return ResponseEntity.status(HttpStatus.OK).body(pm);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(pm);
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
