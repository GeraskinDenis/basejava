package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayStorageTest extends AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_UUID_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_UUID_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_UUID_3 = new Resume(UUID_3);
    private static final String NOT_EXIST_UUID = "NotExistUUID";

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Before
    public void setUp() {
        Arrays.fill(storage.storage, null);
        storage.storage[0] = RESUME_UUID_1;
        storage.storage[1] = RESUME_UUID_2;
        storage.storage[2] = RESUME_UUID_3;
        storage.size = 3;
    }

    @Test
    @Override
    public void clear() {
        storage.clear();
        Assert.assertArrayEquals(new Resume[10_000], storage.storage);
        Assert.assertEquals(0, storage.size);
    }

    @Test
    @Override
    public void deleteFirst() {
        Resume[] expecteds = new Resume[2];
        expecteds[0] = RESUME_UUID_3;
        expecteds[1] = RESUME_UUID_2;

        storage.delete(UUID_1);
        Resume[] actuals = Arrays.copyOf(storage.storage, 2);
        Assert.assertArrayEquals(expecteds, actuals);
        Assert.assertEquals("The number of elements in storage is different!", 2, storage.size);
    }

    @Test
    @Override
    public void deleteLast() {
        Resume[] expecteds = new Resume[2];
        expecteds[0] = RESUME_UUID_1;
        expecteds[1] = RESUME_UUID_2;

        storage.delete(UUID_3);
        Resume[] actuals = Arrays.copyOf(storage.storage, 2);
        Assert.assertArrayEquals(expecteds, actuals);
        Assert.assertEquals("The number of elements in storage is different!", 2, storage.size);
    }

    @Test
    @Override
    public void deleteMiddle() {
        Resume[] expecteds = new Resume[2];
        expecteds[0] = RESUME_UUID_1;
        expecteds[1] = RESUME_UUID_3;

        storage.delete(UUID_2);
        Resume[] actuals = Arrays.copyOf(storage.storage, 2);
        Assert.assertArrayEquals(expecteds, actuals);
        Assert.assertEquals("The number of elements in storage is different!", 2, storage.size);
    }

    @Test(expected = NotExistStorageException.class)
    @Override
    public void deleteNotExist() {
        storage.delete(NOT_EXIST_UUID);
    }

    @Test
    @Override
    public void get() {
        Assert.assertEquals(RESUME_UUID_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    @Override
    public void getNotExist() {
        storage.get(NOT_EXIST_UUID);
    }

    @Test
    @Override
    public void getAll() {
        Resume[] expected = new Resume[3];
        expected[0] = RESUME_UUID_1;
        expected[1] = RESUME_UUID_2;
        expected[2] = RESUME_UUID_3;
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    @Override
    public void save() {
        Resume resume_uuid_4 = new Resume("uuid4");
        Resume[] expected = new Resume[4];
        expected[0] = RESUME_UUID_1;
        expected[1] = RESUME_UUID_2;
        expected[2] = RESUME_UUID_3;
        expected[3] = resume_uuid_4;

        storage.save(resume_uuid_4);
        Resume[] actuals = Arrays.copyOf(storage.storage, 4);
        Assert.assertArrayEquals(expected, actuals);
        Assert.assertEquals("The number of elements in storage is different!", 4, storage.size);
    }

    @Test(expected = ExistStorageException.class)
    @Override
    public void saveExist() {
        storage.save(RESUME_UUID_1);
    }

    @Test(expected = StorageException.class)
    @Override
    public void saveOverFlow() {
        fillAllStorage();
        storage.save(new Resume("RESUME_UUID_10001"));
    }

    private void fillAllStorage() {
        for (int i = 4; i <= storage.storage.length; i++) {
            storage.save(new Resume("RESUME_UUID_" + i));
        }
    }

    @Test
    @Override
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    @Override
    public void update() {
        Resume new_resume_uuid_1 = new Resume(UUID_1);
        storage.update(new_resume_uuid_1);
        Assert.assertEquals(new_resume_uuid_1, storage.storage[0]);
    }

    @Test(expected = NotExistStorageException.class)
    @Override
    public void updateNotExist() {
        storage.update(new Resume(NOT_EXIST_UUID));
    }

    @Test
    @Override
    public void getIndex() {
        Assert.assertEquals(0, storage.getIndex(UUID_1));
    }

    @Test
    @Override
    public void getIndexNotExistUUID() {
        Assert.assertEquals(-1, storage.getIndex(NOT_EXIST_UUID));
    }
}