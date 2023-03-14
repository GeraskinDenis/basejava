package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage implements Storage {
    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void delete(String uuid) {
        doDelete(getExistSearchKey(uuid));
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getExistSearchKey(uuid));
    }

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.stream(getAll())
                .sorted((r1, r2) -> {
                    int c = r1.getFullName().compareTo(r2.getFullName());
                    return (c != 0) ? c : r1.getUuid().compareTo(r2.getUuid());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save(Resume r) {
        doSave(getNotExistSearchKey(r.getUuid()), r);
    }

    @Override
    public int size() {
        return doSize();
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getExistSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void doClear();

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract Resume[] doGetAll();

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract int doSize();

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
