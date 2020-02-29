package com.gmail.vladbaransky.app.service.impl;

import com.gmail.vladbaransky.app.repository.ConnectionRepository;
import com.gmail.vladbaransky.app.repository.ItemRepository;
import com.gmail.vladbaransky.app.repository.model.Item;
import com.gmail.vladbaransky.app.service.model.ItemDTO;
import com.gmail.vladbaransky.app.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository;
    private ItemRepository itemRepository;

    public ItemServiceImpl(ConnectionRepository connectionRepository,
                           ItemRepository itemRepository) {
        this.connectionRepository = connectionRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void add(ItemDTO itemDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Item item = convertDTOToObject(itemDTO);
                itemRepository.add(connection, item);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                itemRepository.delete(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Item convertDTOToObject(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        return item;
    }
}
