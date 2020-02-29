package com.gmail.vladbaransky.app.service.impl;

import com.gmail.vladbaransky.app.repository.ConnectionRepository;
import com.gmail.vladbaransky.app.repository.ShopRepository;
import com.gmail.vladbaransky.app.repository.model.Shop;
import com.gmail.vladbaransky.app.service.ShopService;
import com.gmail.vladbaransky.app.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ShopServiceImpl implements ShopService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopRepository shopRepository;
    private ConnectionRepository connectionRepository;

    public ShopServiceImpl(ShopRepository shopRepository, ConnectionRepository connectionRepository) {
        this.shopRepository = shopRepository;
        this.connectionRepository = connectionRepository;
    }

    @Override
    public void add(ShopDTO shopDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Shop shop = convertDTOToObject(shopDTO);
                shopRepository.add(connection, shop);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Shop convertDTOToObject(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }
}
