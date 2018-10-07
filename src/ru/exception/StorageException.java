package ru.exception;

import java.sql.SQLException;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(SQLException e) {
        this(e.getMessage(),e.toString());
    }

    public String getUuid() {
        return uuid;
    }
}