package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStorage<SK> implements Storage {
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
        SK searchKey = getExistSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void doClear();

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract Resume[] doGetAll();

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract int doSize();

    protected abstract void doUpdate(SK searchKey, Resume r);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);
}
