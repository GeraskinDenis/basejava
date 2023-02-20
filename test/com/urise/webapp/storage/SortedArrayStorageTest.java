package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID_3);
        storage.save(RESUME_UUID_5);
        storage.save(RESUME_UUID_1);
    }

    @Override
    public void deleteFirst() {
        storage.delete(UUID_1);
        assertArrayEquals(new Resume[]{RESUME_UUID_3, RESUME_UUID_5});
    }

    @Override
    public void deleteMiddle() {
        storage.delete(UUID_3);
        assertArrayEquals(new Resume[]{RESUME_UUID_1, RESUME_UUID_5});
    }

    @Override
    public void deleteLast() {
        storage.delete(UUID_5);
        assertArrayEquals(new Resume[]{RESUME_UUID_1, RESUME_UUID_3});
    }

    @Override
    public void saveAtFirst() {
        Resume resumeUUID0 = new Resume("uuid0");
        storage.save(resumeUUID0);
        Resume[] expecteds = new Resume[]{resumeUUID0, RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5};
        assertArrayEquals(expecteds);
    }

    @Override
    public void saveAtMiddle() {
        Resume resumeUUID2 = new Resume("uuid2");
        storage.save(resumeUUID2);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, resumeUUID2, RESUME_UUID_3, RESUME_UUID_5};
        assertArrayEquals(expecteds);
    }

    @Override
    public void saveAtEnd() {
        Resume resumeUUID6 = new Resume("uuid6");
        storage.save(resumeUUID6);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5, resumeUUID6};
        assertArrayEquals(expecteds);
    }
}