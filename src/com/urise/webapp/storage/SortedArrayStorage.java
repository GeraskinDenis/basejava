package com.urise.webapp.storage;

import com.urise.webapp.model.resume.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(Integer searchKey) {
        size--;
        if (searchKey != size) {
            System.arraycopy(storage, searchKey + 1, storage, searchKey, size - searchKey);
        } else {
            storage[searchKey] = null;
        }
    }

    @Override
    protected Integer getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "EmptyName"));
    }

    @Override
    protected void saveResume(Integer index, Resume resume) {
        index = -index - 1;
        if (index != size) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        size++;
    }
}
