package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка! В базе отсутствует резюме с uuid = " + uuid);
            return;
        }
        deleteResume(index);
        size--;
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка! В базе отсутствует резюме с uuid = " + uuid);
            return null;
        }
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public final void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения! Закончилось место в хранилище.");
            return;
        }
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Ошибка сохранения! Резюме с uuid = '" + uuid + "' уже присутствует в хранилище.");
            return;
        }
        index = -1 - index;
        saveResume(index, resume);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка обновления резюме! В базе не найдено резюме с uuid = " + resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    protected abstract void deleteResume(int index);

    public abstract int getIndex(String uuid);

    protected abstract void saveResume(int index, Resume resume);
}
