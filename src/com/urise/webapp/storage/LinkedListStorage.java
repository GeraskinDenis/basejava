package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;

public class LinkedListStorage extends AbstractStorage {
    private final LinkedList<Resume> storage;

    public LinkedListStorage() {
        storage = new LinkedList<>();
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Resume) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.stream().filter(e -> e.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.set(storage.indexOf((Resume) searchKey), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
