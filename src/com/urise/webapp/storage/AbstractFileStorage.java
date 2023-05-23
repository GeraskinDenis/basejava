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
        File[] files = getAllFiles();

        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doDelete(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            throw new StorageException("doDelete() error: " + file.getAbsolutePath(), "No uuid", e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return readFile(file);
        } catch (IOException e) {
            throw new StorageException("Resume read file error: " + file.getAbsolutePath(), "No uuid", e);
        }
    }

    @Override
    protected Resume[] doGetAll() {
        File[] files = getAllFiles();
        Resume[] resumes = new Resume[files.length];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = doGet(files[i]);
        }
        return resumes;
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        return Arrays.stream(getAll()).sorted(Resume.UUID_COMPARATOR)
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
        return getAllFiles().length;
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

    private File[] getAllFiles() {
        File[] files;
        try {
            files = directory.listFiles(File::isFile);
        } catch (Exception e) {
            throw new StorageException("I/O error: getAllFiles()", "No uuid", e);
        }
        return files;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume readFile(File file) throws IOException;
}
