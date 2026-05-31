package com.proiect;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private static AuditService instance;
    private final String CSV_FILE_PATH = "audit.csv";

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logAction(String actionName) {
        LocalDateTime acum = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = acum.format(format);


        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            writer.write(actionName + "," + timestamp + "\n");
            
        } catch (IOException e) {
            System.out.println("[Eroare Audit] Nu s-a putut scrie în fișierul CSV: " + e.getMessage());
        }
    }
}