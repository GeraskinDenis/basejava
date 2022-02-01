package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private static final int STORAGE_LIMIT = 10_000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка! В базе отсутствует резюме с uuid = " + uuid);
            return;
        }
        size--;
        storage[index] = storage[size];
        storage[size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            storage[size++] = resume;
        } else {
            System.out.println("Ошибка сохранения! Резюме с uuid = '" + resume.getUuid() + "' уже присутствует в хранилище.");
        }
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка обновления резюме! В базе не найдено резюме с uuid = " + resume.getUuid());
            return;
        }
        storage[index] = resume;
    }
}
