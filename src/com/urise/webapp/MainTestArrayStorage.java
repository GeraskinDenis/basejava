package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid1");
        final Resume r2 = new Resume();
        r2.setUuid("uuid2");
        final Resume r3 = new Resume();
        r3.setUuid("uuid3");

        // save()
        System.out.println();
        System.out.println("Test of save()");
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        // Error save
        ARRAY_STORAGE.save(r3);

        // get()
        System.out.println();
        System.out.println("Test of get()");
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        // getAll()
        System.out.println("\ngetAll()");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }


        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        //update
        ARRAY_STORAGE.update(r1);
        ARRAY_STORAGE.save(r1);
        printAll();
        ARRAY_STORAGE.save(r1);

        ARRAY_STORAGE.clear();
        printAll();


        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
