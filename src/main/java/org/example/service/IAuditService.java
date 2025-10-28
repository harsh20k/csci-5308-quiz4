package org.example.service;

public interface IAuditService {
    void logInfo(String eventType, String message);
    void logError(String eventType, String message);
}
