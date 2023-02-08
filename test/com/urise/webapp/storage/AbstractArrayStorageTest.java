package com.urise.webapp.storage;

public abstract class AbstractArrayStorageTest {
    protected final AbstractArrayStorage storage;

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    public abstract void clear();

    public abstract void deleteFirst();

    public abstract void deleteLast();

    public abstract void deleteMiddle();

    public abstract void deleteNotExist();

    public abstract void get();

    public abstract void getNotExist();

    public abstract void getAll();

    public abstract void save();

    public abstract void saveExist();

    public abstract void saveOverFlow();

    public abstract void size();

    public abstract void update();

    public abstract void updateNotExist();

    public abstract void getIndex();

    public abstract void getIndexNotExistUUID();
}