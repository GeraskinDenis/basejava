package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.resume.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void doDelete(Integer searchKey) {
        deleteResume(searchKey);
    }

    @Override
    public final Resume doGet(Integer index) {

        return storage[index];
    }

    @Override
    public Resume[] doGetAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected List<Resume> doGetAllSorted() {
        List<Resume> resumes = Arrays.asList(Arrays.copyOf(storage, size));
        resumes.sort(RESUME_COMPARATOR_BY_NAME_BY_UUID);
        return resumes;
    }

    @Override
    public final void doSave(Integer searchKey, Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            throw new StorageException("The storage is overflow!", r.getUuid());
        }
        saveResume(searchKey, r);
    }

    @Override
    public int doSize() {
        return size;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume r) {
        storage[searchKey] = r;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey > -1;
    }

    protected abstract void deleteResume(Integer searchKey);

    protected abstract Integer getIndex(String uuid);

    protected abstract void saveResume(Integer index, Resume resume);
}
