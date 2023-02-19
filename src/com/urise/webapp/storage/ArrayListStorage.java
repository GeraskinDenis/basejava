package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ArrayListStorage extends AbstractStorage {

    private final List<Resume> storage;

    public ArrayListStorage() {
        storage = new ArrayList<>();
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.set((int) index, r);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
