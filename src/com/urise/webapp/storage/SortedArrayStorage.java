package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int searchKey) {
        size--;
        if (searchKey != size) {
            System.arraycopy(storage, searchKey + 1, storage, searchKey, size - searchKey);
        } else {
            storage[searchKey] = null;
        }
    }

    @Override
    public int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "EmptyName"));
    }

    @Override
    protected void saveResume(int index, Resume resume) {
        index = -index - 1;
        if (index != size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        size++;
    }
}
