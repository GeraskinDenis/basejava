package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected final List<Resume> storage;

    public AbstractStorage(List<Resume> storage) {
        this.storage = storage;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void delete(String uuid) {
        Resume resume = get(uuid);
        if (resume != null) {
            storage.remove(resume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        return storage.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Resume resumeInStorage = get(uuid);
        if (resumeInStorage == null) {
            storage.add(resume);
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Resume resumeInStorage = get(uuid);
        if (resumeInStorage == null) {
            throw new NotExistStorageException(uuid);
        }
        storage.add(storage.indexOf(resumeInStorage), resume);
    }
}
