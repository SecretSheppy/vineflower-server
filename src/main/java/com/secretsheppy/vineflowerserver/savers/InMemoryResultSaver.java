package com.secretsheppy.vineflowerserver.savers;

import org.jetbrains.java.decompiler.main.extern.IResultSaver;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

public class InMemoryResultSaver implements IResultSaver {
    private Map<String, String> results = new HashMap<>();

    @Override
    public void saveClassFile(String path, String qualifiedName, String entryName, String content, int[] mapping) {
        results.put(qualifiedName, content);
    }

    @Override
    public void saveFolder(String path) {}

    @Override
    public void copyFile(String source, String path, String entryName) {}

    @Override
    public void createArchive(String path, String archiveName, Manifest manifest) {}

    @Override
    public void saveDirEntry(String path, String archiveName, String entryName) {}

    @Override
    public void copyEntry(String source, String path, String archiveName, String entry) {}

    @Override
    public void saveClassEntry(String path, String archiveName, String qualifiedName, String entryName, String content) {}

    @Override
    public void closeArchive(String path, String archiveName) {}

    public Map<String, String> getResults() {
        return results;
    }
}
