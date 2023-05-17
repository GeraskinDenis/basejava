package com.urise.webapp.storage;

import com.urise.webapp.model.resume.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(Integer searchKey) {
        size--;
        storage[searchKey] = storage[size];
        storage[size] = null;
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveResume(Integer index, Resume resume) {
        storage[size] = resume;
        size++;
    }
}
