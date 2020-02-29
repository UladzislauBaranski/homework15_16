package com.gmail.vladbaransky.app.repository.impl;

import com.gmail.vladbaransky.app.repository.ShopRepository;
import com.gmail.vladbaransky.app.repository.model.Shop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ShopRepositoryImpl extends GeneralRepositoryImpl<Shop> implements ShopRepository {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void add(Connection connection, Shop shop) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO shop(name, location) VALUES (?,?)"
        )) {
            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getLocation());
            int rowAffected = preparedStatement.executeUpdate();
            logger.info("Row affected:" + rowAffected);
        } catch (SQLException e) {
            logger.error("Failed to insert shop");
        }
    }

    @Override
    public void delete(Connection connection) {
        throw new UnsupportedOperationException();
    }
}
