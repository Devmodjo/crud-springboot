package cm.inaf.produit.service;


import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.Dto.ProduitResponseDto;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.repository.ProduitRepository;
import cm.inaf.produit.service.serviceImplement.ProduitImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImplementTest {


    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitImpl produitService;

    private ProduitModel produitModel;

    private ProduitRequestDto produitRequestDto;

    private final int productid = 21;


    @BeforeEach
    void initData() {
        produitModel = new ProduitModel(productid, "tomate", "très juteux", 50.5, LocalDate.now());
        produitRequestDto = new ProduitRequestDto("tomate", "très juteux", 50.5, LocalDate.now());
    }

    @Test
    void shouldCreateProduct__success() {
        when(produitRepository.save(any(ProduitModel.class))).thenReturn(produitModel);

        ProduitResponseDto res = produitService.saveProduct(produitRequestDto);

        assertNotNull(res);
        assertEquals(res.id_(), productid);
        assertEquals(res.produtName(), produitModel.getProductName());
        assertEquals(res.dateRegister(), produitModel.getDateRegister());
        assertEquals(res.price(), produitModel.getPrice());

        verify(produitRepository, times(1)).save(any(ProduitModel.class));

    }

    @Test
    void shouldGetAllProduct__success() {
        when(produitRepository.findAll()).thenReturn(List.of(produitModel));

        List<ProduitResponseDto> res = produitService.getAllProduct();

        assertNotNull(res);
        assertEquals(res.get(0).id_(), productid);
        assertEquals(res.get(0).produtName(), produitModel.getProductName());

        verify(produitRepository).findAll();

    }

    @Test
    void shouldGetProductById__sucess() {
        when(produitRepository.findById(productid)).thenReturn(Optional.of(produitModel));

        ProduitResponseDto res = produitService.getProduct(productid);

        assertNotNull(res);
        assertEquals(res.produtName(), produitModel.getProductName());
        assertEquals(res.id_(), productid);
        assertEquals(res.dateRegister(), produitModel.getDateRegister());

        verify(produitRepository).findById(productid);
    }

    @Test
    void shouldUpdateProduct__success() {
        when(produitRepository.findById(productid)).thenReturn(Optional.of(produitModel));
        when(produitRepository.save(any(ProduitModel.class))).thenReturn(produitModel);

        ProduitResponseDto res = produitService.updateProduct(productid, produitRequestDto);
        assertNotNull(res);
        assertEquals(res.id_(), productid);
        assertEquals(res.description(), produitModel.getDescription());

        verify(produitRepository).findById(productid);
        verify(produitRepository, times(1)).save(any(ProduitModel.class));
    }


    @Test
    void shouldDeleteProduct__success() {
        when(produitRepository.findById(productid)).thenReturn(Optional.of(produitModel));
        doNothing().when(produitRepository).delete(produitModel);

        Boolean res = produitService.deleteProductById(productid);

        assertNotNull(res);
        verify(produitRepository).delete(produitModel);
    }
}
