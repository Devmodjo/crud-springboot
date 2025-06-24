package cm.inaf.produit.mapper;

import cm.inaf.produit.Dto.ProduitRequestDto;
import cm.inaf.produit.Dto.ProduitResponseDto;
import cm.inaf.produit.model.ProduitModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProduitMapper {


    private static final Logger log = LoggerFactory.getLogger(ProduitMapper.class);

    public ProduitModel toEntity(ProduitRequestDto dto) {
        if (dto == null) {
            log.error("convert Dto to Entity");
            return null;
        }

        log.info("convert Dto to Entity");
        ProduitModel pm = new ProduitModel();
        pm.setProductName(dto.produtName());
        pm.setPrice(dto.price());
        pm.setDescription(dto.description());
        pm.setDateRegister(dto.dateRegister());

        return pm;
    }

    public ProduitResponseDto toDto(ProduitModel entity) {

        if (entity == null) {
            log.error("failed convert Entity to Dto");
            return null;
        }
        log.info("convert Entity to Dto");
        return new ProduitResponseDto(
                entity.getId_(), entity.getProductName(), entity.getDescription(), entity.getPrice(), entity.getDateRegister()
        );

    }
}
