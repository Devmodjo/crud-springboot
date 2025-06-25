package cm.inaf.produit.controller;


import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.repository.ProduitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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


    public int productid;

    @BeforeEach()
    void initData() {
        produitRepository.deleteAll();
        produitModel = new ProduitModel(null, "vinosol", "vin de palme", 1200.0, LocalDate.now());
        productid = produitRepository.save(produitModel).getId_();
    }

    @Test
    @DisplayName(value = "PRODUCT -  /api/products/save should save product ")
    void saveProductApi() throws Exception {

        ProduitRequestDto requestDto = new ProduitRequestDto("savon", "très moussant", 500.5, LocalDate.now());
        mockMvc.perform(post("/api/products/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtName").value("savon"))
                .andExpect(jsonPath("$.description").value("très moussant"));
    }

    @Test
    @DisplayName(value = "PRODUCT - /api/products/get -should get all product in database")
    void getProductApi() throws Exception {
        mockMvc.perform(get("/api/products/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].produtName").value(produitModel.getProductName()))
                .andExpect(jsonPath("$[0].description").value(produitModel.getDescription()));
    }

    @Test
    @DisplayName(value = "PRODUCT - /api/products/update - should updating product by id")
    void updateProductApi() throws Exception {
        ProduitRequestDto requestDto = new ProduitRequestDto("huile de toilet", "doux sur la peau", 1500.0, LocalDate.now());
        mockMvc.perform(put("/api/products/update/" + productid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtName").value("huile de toilet"))
                .andExpect(jsonPath("$.description").value("doux sur la peau"));
    }

    @Test
    @DisplayName(value = "PRODUCT - /api/products/delete/{id} - should delete an existing product")
    void deleteProductApi() throws Exception {
        mockMvc.perform(delete("/api/products/delete/" + productid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


}
