package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void deleteResume(int index) {
        storage[index] = storage[size];
        storage[size] = null;
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void saveResume(int index, Resume resume) {
        storage[size] = resume;
    }
}
