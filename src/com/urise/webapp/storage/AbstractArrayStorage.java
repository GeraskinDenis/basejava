package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
    }

    @Override
    public final Resume doGet(Object index) {
        return storage[(int) index];
    }

    @Override
    public Resume[] doGetAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public final void doSave(Object searchKey, Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            throw new StorageException("The storage is overflow!", r.getUuid());
        }
        saveResume((int) searchKey, r);
    }

    @Override
    public int doSize() {
        return size;
    }

    @Override
    public final void doUpdate(Object searchKey, Resume r) {
        storage[(int) searchKey] = r;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey > -1;
    }

    protected abstract void deleteResume(int index);

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(int index, Resume resume);
}
