package com.gmail.vladbaransky.app.repository.impl;

import com.gmail.vladbaransky.app.repository.ItemShopRepository;
import com.gmail.vladbaransky.app.repository.model.ItemShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ItemShopRepositoryImpl extends GeneralRepositoryImpl<ItemShop> implements ItemShopRepository {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void addRelations(Connection connection, ItemShop itemShop) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO item_shop(id_item, id_shop) VALUES (?,?)"
        )) {
            preparedStatement.setInt(1, itemShop.getIdItem());
            preparedStatement.setInt(2, itemShop.getIdShop());
            int rowAffected = preparedStatement.executeUpdate();
            logger.info("Row affected:" + rowAffected);
        } catch (SQLException e) {
            logger.error("Failed to insert item_shop");
        }
    }

    @Override
    public void add(Connection connection, ItemShop itemShop) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Connection connection) {
        throw new UnsupportedOperationException();
    }
}

