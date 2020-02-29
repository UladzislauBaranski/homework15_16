package com.gmail.vladbaransky.app.service.impl;

import com.gmail.vladbaransky.app.repository.ConnectionRepository;
import com.gmail.vladbaransky.app.repository.ItemShopRepository;
import com.gmail.vladbaransky.app.repository.model.ItemShop;
import com.gmail.vladbaransky.app.service.ItemShopService;
import com.gmail.vladbaransky.app.service.model.ItemShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ItemShopServiceImpl implements ItemShopService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository;
    private final ItemShopRepository itemShopRepository;

    public ItemShopServiceImpl(ConnectionRepository connectionRepository, ItemShopRepository itemShopRepository) {
        this.connectionRepository = connectionRepository;
        this.itemShopRepository = itemShopRepository;
    }

    @Override
    public void addRelations(ItemShopDTO itemShopDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                ItemShop itemShop = convertDTOToObject(itemShopDTO);
                itemShopRepository.addRelations(connection, itemShop);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private ItemShop convertDTOToObject(ItemShopDTO itemShopDTO) {
        ItemShop itemShop = new ItemShop();
        itemShop.setId(itemShopDTO.getId());
        itemShop.setIdItem(itemShopDTO.getIdItem());
        itemShop.setIdShop(itemShopDTO.getIdShop());
        return itemShop;
    }
}

