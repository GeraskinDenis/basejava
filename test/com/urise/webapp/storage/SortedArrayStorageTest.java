package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
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
        storage.save(RESUME_UUID_7);
    }

    @Override
    public void deleteFirst() {
        storage.delete(UUID_1);
        assertArrayEquals(new Resume[]{RESUME_UUID_3, RESUME_UUID_5, RESUME_UUID_7});
    }

    @Override
    public void deleteMiddle() {
        storage.delete(UUID_3);
        assertArrayEquals(new Resume[]{RESUME_UUID_1, RESUME_UUID_5, RESUME_UUID_7});
    }

    @Override
    public void deleteLast() {
        storage.delete(UUID_7);
        assertArrayEquals(new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5});
    }

    @Override
    public void getIndex() {
        Assert.assertEquals(0, ((AbstractArrayStorage) storage).getIndex(UUID_1));
        Assert.assertEquals(1, ((AbstractArrayStorage) storage).getIndex(UUID_3));
        Assert.assertEquals(2, ((AbstractArrayStorage) storage).getIndex(UUID_5));
        Assert.assertEquals(3, ((AbstractArrayStorage) storage).getIndex(UUID_7));
    }

    @Override
    public void getIndexNotExistUUID() {
        Assert.assertEquals(-5, ((AbstractArrayStorage) storage).getIndex(UUID_NOT_EXIST));
    }

    @Override
    public void saveAtFirst() {
        Resume resumeUUID0 = new Resume("uuid0", "FullName0");
        storage.save(resumeUUID0);
        Resume[] expecteds = new Resume[]{resumeUUID0, RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5, RESUME_UUID_7};
        assertArrayEquals(expecteds);
    }

    @Override
    public void saveAtMiddle() {
        Resume resumeUUID2 = new Resume("uuid2", "FullName2");
        storage.save(resumeUUID2);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, resumeUUID2, RESUME_UUID_3, RESUME_UUID_5, RESUME_UUID_7};
        assertArrayEquals(expecteds);
    }

    @Override
    public void saveAtEnd() {
        Resume resumeUUID8 = new Resume("uuid8", "FullName8");
        storage.save(resumeUUID8);
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5, RESUME_UUID_7, resumeUUID8};
        assertArrayEquals(expecteds);
    }
}