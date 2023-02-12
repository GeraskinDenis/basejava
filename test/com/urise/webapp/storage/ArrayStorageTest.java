package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Override
    public void deleteFirst() {
        storage.delete(UUID_1);
        assertDelete(new Resume[]{RESUME_UUID_5, RESUME_UUID_3});
    }

    @Override
    public void deleteMiddle() {
        storage.delete(UUID_3);
        assertDelete(new Resume[]{RESUME_UUID_1, RESUME_UUID_5});
    }

    @Override
    public void deleteLast() {
        storage.delete(UUID_5);
        assertDelete(new Resume[]{RESUME_UUID_1, RESUME_UUID_3});
    }
}