package com.foodstore.app.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.foodstore.app.converter.DozerHelper;
import com.foodstore.app.dao.ISanduicheDAO;
import com.foodstore.app.dto.SanduicheDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.entity.SanduicheEntity;
import com.foodstore.app.exception.BusinessException;
import com.foodstore.app.service.ISanduicheService;

@Service
public class SanduicheServiceImpl implements ISanduicheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SanduicheServiceImpl.class);

    @Autowired
    private ISanduicheDAO sanduicheDAO;

    @Override
    public List<SanduicheDTO> buscarSanduiches() {

        final List<SanduicheEntity> sanduiches = this.sanduicheDAO.buscarTodosSanduiches();

        for (final SanduicheEntity sanduicheEntity : sanduiches) {
            sanduicheEntity.setValorSanduiche(this.calculaValorSanduiche(sanduicheEntity));
        }

        return DozerHelper.map(sanduiches, SanduicheDTO.class);
    }

    @Override
    public SanduicheDTO buscarSanduichePorCodigo(final Integer codigoSanduiche) {

        try {
            final SanduicheEntity sanduiche = this.sanduicheDAO.buscarSanduichePorCodigo(codigoSanduiche);

            sanduiche.setValorSanduiche(this.calculaValorSanduiche(sanduiche));

            return DozerHelper.map(sanduiche, SanduicheDTO.class);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Sanduiche não encontrado.", e);
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Calcula o valor básico do sanduiche, de acordo com o valor e a quantidade de cada ingrediente.
     *
     * @param sanduiches o sanduiche
     * @return o valor calculado
     */
    private BigDecimal calculaValorSanduiche(final SanduicheEntity sanduiches) {

        BigDecimal valorSanduiche = BigDecimal.ZERO;

        for (final IngredienteEntity ingrediente : sanduiches.getIngredientes()) {
            valorSanduiche = valorSanduiche.add(ingrediente.getValorIngrediente());
        }

        return valorSanduiche;

    }

}
