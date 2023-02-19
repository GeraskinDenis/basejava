package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortedArrayStorageTest extends AbstractStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void deleteFirst() {
        storage.delete(UUID_1);
        assertDelete(new Resume[]{RESUME_UUID_3, RESUME_UUID_5});
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

    @Test
    public void saveAtFirst() {
        Resume resumeUUID0 = new Resume("uuid0");
        storage.save(resumeUUID0);
        Resume[] expecteds = new Resume[]{resumeUUID0, RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5};
        assertSave(expecteds);
    }

    @Test
    public void saveAtMiddle() {
        Resume resumeUUID2 = new Resume("uuid2");
        storage.save(resumeUUID2);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, resumeUUID2, RESUME_UUID_3, RESUME_UUID_5};
        assertSave(expecteds);
    }

    @Test
    public void saveAtEnd() {
        Resume resumeUUID6 = new Resume("uuid6");
        storage.save(resumeUUID6);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5, resumeUUID6};
        assertSave(expecteds);
    }

    private void assertDelete(Resume[] expecteds) {
        Resume[] actuals = storage.getAll();
        Assert.assertArrayEquals(expecteds, actuals);
        assertSize(expecteds.length);
    }

    private void assertSave(Resume[] expecteds) {
        Assert.assertArrayEquals(expecteds, storage.getAll());
        assertSize(expecteds.length);
    }
}