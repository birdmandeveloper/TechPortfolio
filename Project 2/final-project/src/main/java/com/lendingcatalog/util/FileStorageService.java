package com.lendingcatalog.util;

import com.lendingcatalog.model.Member;
import com.lendingcatalog.util.exception.FileStorageException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorageService {

    private static final String LOG_PATH = "src/main/resources/logs/";

    private String filename;

    public FileStorageService(String filename) {
        this.filename = filename;
    }

    public void writeContentsToFile(String contents, boolean appendFile) throws FileStorageException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_PATH + filename, appendFile))) {
            bw.write(contents + System.lineSeparator());
        } catch (IOException e) {
            throw new FileStorageException("Failed to write to file: " + filename, e);
        }
    }

    public List<String> readContentsOfFile() throws FileStorageException {
        List<String> contents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_PATH + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (IOException e) {
            throw new FileStorageException("Failed to read from file: " + filename, e);
        }
        return contents;
    }

    public List<Member> loadAll() {
        return null;
    }
}
