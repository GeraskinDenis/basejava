package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.resume.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "The parameter 'directory' must not be null.");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("'" + directory.getAbsolutePath() + "'" + " is not directory.");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("'" + directory.getAbsolutePath() + "'" + " is not readable / writable.");
        }
        this.directory = directory;
    }

    public File getDirectory() {
        return directory;
    }

    @Override
    protected void doClear() {
        File[] files = directory.listFiles();

        if (Objects.isNull(files)) {
            return;
        }

        Arrays.stream(files).filter(File::exists)
                .filter(File::isFile)
                .forEach(File::delete);
    }

    @Override
    protected void doDelete(File file) {
        Objects.requireNonNull(file, "The parameter 'file' must not be null!");
        file.delete();
    }

    @Override
    protected Resume doGet(File file) {
        Objects.requireNonNull(file, "The parameter 'file' must not be null!");
        return readFile(file);
    }

    @Override
    protected Resume[] doGetAll() {
        return Arrays.stream(directory.listFiles())
                .filter(File::exists)
                .filter(File::isFile)
                .map(this::readFile)
                .toArray(Resume[]::new);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return Arrays.stream(directory.listFiles())
                .filter(File::exists)
                .filter(File::isFile)
                .map(this::readFile)
                .sorted(Resume.UUID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    protected void doSave(File file, Resume r) {
        Objects.requireNonNull(file, "The parameter 'file' must not be null!");
        Objects.requireNonNull(r, "The parameter 'r' must not be null!");
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected int doSize() {
        return (int) Arrays.stream(directory.listFiles())
                .filter(File::exists)
                .filter(File::isFile)
                .count();
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume readFile(File file);
}
