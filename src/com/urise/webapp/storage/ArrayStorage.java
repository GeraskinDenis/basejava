package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void deleteByIndex(int deleteIndex) {
        size--;
        storage[deleteIndex] = storage[size];
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
    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            return;
        }
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index < 0) {
            storage[size++] = resume;
        } else {
            System.out.println("Ошибка сохранения! Резюме с uuid = '" + uuid + "' уже присутствует в хранилище.");
        }
    }
}
