package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        size--;
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            storage[index] = null;
        }
    }

    @Override
    public int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
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
