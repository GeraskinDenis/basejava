package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void saveResume(int index, Resume resume) {
        int indexSave = -1 - index;
        moveRight(indexSave);
        storage[indexSave] = resume;
    }

    private void moveRight(int from) {
        for (int i = size; i > from; i--) {
            storage[i] = storage[i - 1];
        }
        storage[from] = null;
    }

}
