package cm.inaf.produit.controller;


import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.repository.ProduitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ProduitModel produitModel;

    private ProduitRequestDto requestDto;


    @BeforeEach()
    void initData(){

    }


}
