package com.gmail.vladbaransky.app.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionRepository {
    Connection getConnection() throws SQLException;
}
