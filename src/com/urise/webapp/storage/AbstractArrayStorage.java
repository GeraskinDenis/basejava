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
        int deleteIndex = getIndex(uuid);
        if (deleteIndex < 0) {
            System.out.println("Ошибка! В базе отсутствует резюме с uuid = " + uuid);
            return;
        }
        deleteByIndex(deleteIndex);
    }

    protected abstract void deleteByIndex(int deleteIndex);

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

    public abstract int getIndex(String uuid);

    @Override
    public int size() {
        return size;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка обновления резюме! В базе не найдено резюме с uuid = " + resume.getUuid());
            return;
        }
        storage[index] = resume;
    }
}
