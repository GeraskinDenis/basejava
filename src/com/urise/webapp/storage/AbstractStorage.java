package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

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

    /**
     * @param searchKey
     * @param r
     * @return
     */
    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract int doSize();

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
