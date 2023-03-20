package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        resumes.sort(RESUME_COMPARATOR_BY_NAME_BY_UUID);
        return resumes;
    }

    @Override
    protected void doSave(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected void doUpdate(String searchKey, Resume r) {
        storage.replace(searchKey, r);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }
}
