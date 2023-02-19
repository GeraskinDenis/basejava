package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayStorageTest extends AbstractStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void deleteFirst() {
        storage.delete(UUID_1);
        assertDelete(new Resume[]{RESUME_UUID_5, RESUME_UUID_3});
    }

    @Test
    public void deleteMiddle() {
        storage.delete(UUID_3);
        assertDelete(new Resume[]{RESUME_UUID_1, RESUME_UUID_5});
    }

    @Test
    public void deleteLast() {
        storage.delete(UUID_5);
        assertDelete(new Resume[]{RESUME_UUID_1, RESUME_UUID_3});
    }

    private void assertDelete(Resume[] expecteds) {
        Resume[] actuals = storage.getAll();
        Assert.assertArrayEquals(expecteds, actuals);
        assertSize(expecteds.length);
    }
}