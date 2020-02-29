package com.gmail.vladbaransky.app.repository;

import com.gmail.vladbaransky.app.repository.model.ItemShop;

import java.sql.Connection;

public interface ItemShopRepository extends GeneralRepository<ItemShop> {
    void addRelations(Connection connection, ItemShop itemShop);
}
