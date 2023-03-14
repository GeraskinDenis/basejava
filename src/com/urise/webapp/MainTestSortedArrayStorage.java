package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestSortedArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");
        final Resume r4 = new Resume("uuid4");
        final Resume r5 = new Resume("uuid5");
        final Resume r6 = new Resume("uuid6");

        System.out.println("\n*** Testing the save() method ***");
        ARRAY_STORAGE.save(r6);
        System.out.println(r6 + " save done!");
        ARRAY_STORAGE.save(r2);
        System.out.println(r2 + " save done!");
        ARRAY_STORAGE.save(r1);
        System.out.println(r1 + " save done!");
        ARRAY_STORAGE.save(r4);
        System.out.println(r4 + " save done!");
        ARRAY_STORAGE.save(r3);
        System.out.println(r3 + " save done!");
        ARRAY_STORAGE.save(r5);
        System.out.println(r5 + " save done!");
        System.out.println("Attempt to re-save resume (uuid3)");
        ARRAY_STORAGE.save(r3);
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\n*** Testing the delete (\"uuid2)\" method ***");
        ARRAY_STORAGE.delete("uuid2");
        System.out.println("Deleting completed!");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\n*** Testing the delete (\"uuid1)\" method ***");
        ARRAY_STORAGE.delete("uuid1");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\n*** Testing the delete (\"uuid6)\" method ***");
        ARRAY_STORAGE.delete("uuid6");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\nTrying to delete resume again (uuid2)");
        ARRAY_STORAGE.delete("uuid2");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\n*** Testing the save() method ***");
        ARRAY_STORAGE.save(r6);
        System.out.println(r6 + " save done!");
        ARRAY_STORAGE.save(r2);
        System.out.println(r2 + " save done!");
        ARRAY_STORAGE.save(r1);
        System.out.println(r1 + " save done!");
        ARRAY_STORAGE.save(r4);
        System.out.println(r4 + " save done!");
        ARRAY_STORAGE.save(r3);
        System.out.println(r3 + " save done!");
        ARRAY_STORAGE.save(r5);
        System.out.println(r5 + " save done!");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("\n*** Testing the get(\"uuid3\") method ***");
        Resume resultOfSearch = ARRAY_STORAGE.get("uuid3");
        System.out.println("Result of get(\"uuid3\"): " + resultOfSearch);
        System.out.println("\n*** Testing the get(\"dummy\") method ***");
        resultOfSearch = ARRAY_STORAGE.get("dummy");
        System.out.println("Result of get(\"dummy\"): " + resultOfSearch);

        System.out.println("\n*** Test of getAll() method***");
        ARRAY_STORAGE.getAllSorted().forEach(System.out::println);

        System.out.println("\n*** Testing the delete(\"uuid5\") method***");
        ARRAY_STORAGE.delete("uuid5");
        System.out.println("Deleting has complated!");
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());


        System.out.println("\n*** Testing the update(newR1) ***");
        Resume newR1 = new Resume("uuid1");
        ARRAY_STORAGE.update(newR1);

        System.out.println("\n*** Testing the clear() method***");
        System.out.println("Size before clear(): " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        System.out.println("Size after clear(): " + ARRAY_STORAGE.size());

        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("Print All");
        ARRAY_STORAGE.getAllSorted().forEach(System.out::println);
    }
}
