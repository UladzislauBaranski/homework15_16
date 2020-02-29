package com.gmail.vladbaransky.app.repository.impl;

import com.gmail.vladbaransky.app.repository.ItemRepository;
import com.gmail.vladbaransky.app.repository.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ItemRepositoryImpl extends GeneralRepositoryImpl<Item> implements ItemRepository {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void add(Connection connection, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO item(name, description) VALUES (?,?)"
        )) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            int rowAffected = preparedStatement.executeUpdate();
            logger.info("Row affected:add " + rowAffected);
        } catch (SQLException e) {
            logger.error("Failed to insert item");
        }
    }

    @Override
    public void delete(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM item WHERE id in (SELECT id_item from item_shop where id_shop is not NULL )"
        )) {
            int rowAffected = preparedStatement.executeUpdate();
            logger.info("Row affected:" + rowAffected);
        } catch (SQLException e) {
            logger.info("Failed to delete item");
        }
    }
}
