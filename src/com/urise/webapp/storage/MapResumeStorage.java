package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return searchKey;
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
    protected void doSave(Resume searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Resume searchKey, Resume r) {
        storage.replace(searchKey.getUuid(), r);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return Objects.nonNull(searchKey);
    }
}
