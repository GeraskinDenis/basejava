package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void delete(String uuid) {
        int deleteIndex = getIndex(uuid);
        if (deleteIndex < 0) {
            System.out.println("Ошибка! В базе отсутствует резюме с uuid = " + uuid);
            return;
        }
        moveLeft(deleteIndex);
    }

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void save(Resume resume) {

        if (size == STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            return;
        }

        if (size == 0) {
            storage[0] = resume;
            size++;
            return;
        }

        int resultOfBinarySearch = Arrays.binarySearch(storage, 0, size, resume);

        if (resultOfBinarySearch == -1) {
            moveRight();
            storage[0] = resume;
        } else if (resultOfBinarySearch < -size) {
            storage[size++] = resume;
        } else if (resultOfBinarySearch >= 0) {
            System.out.println("Ошибка сохранения! Резюме с uuid = '" + resume.getUuid() + "' уже присутствует в хранилище.");
        } else {
            int indexOfInsert = -(resultOfBinarySearch + 1);
            moveRight(indexOfInsert);
            storage[indexOfInsert] = resume;
        }
    }

    private void moveRight() {
        moveRight(0);
    }

    private void moveRight(int from) {
        for (int i = size; i > from; i--) {
            storage[i] = storage[i - 1];
        }
        storage[from] = null;
        size++;
    }

    private void moveLeft(int deleteIndex) {
        size--;
        for (int i = deleteIndex; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size] = null;
    }
}
