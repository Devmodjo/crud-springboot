package cm.inaf.produit.service.serviceImplement;

import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.Dto.ProduitResponseDto;
import cm.inaf.produit.exception.ResourceNotFoundException;
import cm.inaf.produit.mapper.ProduitMapper;
import cm.inaf.produit.model.ProduitModel;
import cm.inaf.produit.repository.ProduitRepository;
import cm.inaf.produit.service.ProduitService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class ProduitImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private static final Logger log = LoggerFactory.getLogger(ProduitImpl.class);

    @Autowired
    private ProduitMapper produitMapper = new ProduitMapper();

    public ProduitImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitResponseDto saveProduct(ProduitRequestDto produitRequestDto) {
        log.info("create product");
        ProduitModel entity = produitMapper.toEntity(produitRequestDto);
        var saved = produitRepository.save(entity);
        log.error("failed to create product");
        return produitMapper.toDto(saved);
    }

    @Override
    public List<ProduitResponseDto> getAllProduct() {
        log.info("get products");
        List<ProduitResponseDto> list = new ArrayList<>();
        produitRepository.findAll().forEach((el) -> {
            list.add(produitMapper.toDto(el));
        });
        log.error("failed to get product");
        return list;
    }

    @Override
    public ProduitResponseDto getProduct(Integer id) {
        Optional<ProduitModel> found = produitRepository.findById(id);

        if (found.isPresent()) {
            log.info("update product with id ${id}");
            ProduitModel pm = found.get();
            return produitMapper.toDto(pm);
        }
        log.error("failed to update product");
        throw new ResourceNotFoundException("ce produit n'existe pas !");
    }

    @Override
    public Boolean deleteProductById(int id) {
        Optional<ProduitModel> foundProduct = produitRepository.findById(id);
        if (foundProduct.isPresent()) {
            log.info("delete product");
            produitRepository.delete(foundProduct.get());
            return true;
        }
        return false;
    }

    @Override
    public ProduitResponseDto updateProduct(int id, ProduitRequestDto produitRequestDto) {
        Optional<ProduitModel> found = produitRepository.findById(id);
        if (found.isPresent()) {
            log.info("update product");
            ProduitModel produitModel = found.get();
            produitModel.setProductName(produitRequestDto.produtName());
            produitModel.setDescription(produitRequestDto.description());
            produitModel.setDateRegister(produitRequestDto.dateRegister());
            produitModel.setPrice(produitRequestDto.price());

            ProduitModel updated = produitRepository.save(produitModel);
            return produitMapper.toDto(updated);
        }
        log.info("failed to update product");
        throw new ResourceNotFoundException("ce produit n'existe pas !");
    }
}
