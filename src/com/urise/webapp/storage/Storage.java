package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void delete(String uuid);

    Resume get(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    void save(Resume resume);

    int size();

    void update(Resume resume);
}
