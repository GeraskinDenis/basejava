package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void doClear() {
        storage.clear();
    }

    @Override
    public void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public Resume[] doGetAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public void doSave(Object searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    public int doSize() {
        return storage.size();
    }

    @Override
    public void doUpdate(Object searchKey, Resume r) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}
