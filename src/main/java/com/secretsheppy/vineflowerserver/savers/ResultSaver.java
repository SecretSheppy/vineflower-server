package com.secretsheppy.vineflowerserver.savers;

import org.jetbrains.java.decompiler.main.decompiler.DirectoryResultSaver;
import org.jetbrains.java.decompiler.main.decompiler.SingleFileSaver;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;

import java.io.File;
import java.util.Optional;

public class ResultSaver {
    private final InMemoryResultSaver inMemoryResultSaver;

    public ResultSaver() {
        inMemoryResultSaver = new InMemoryResultSaver();
    }

    public IResultSaver IResultSaver(File destination) {
        return Optional.ofNullable(destination)
                .map(dest -> dest.isDirectory() ? new DirectoryResultSaver(dest) : new SingleFileSaver(dest))
                .orElse(inMemoryResultSaver);
    }

    public InMemoryResultSaver getInMemoryResultSaver() {
        return inMemoryResultSaver;
    }
}
