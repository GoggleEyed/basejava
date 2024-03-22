package com.goggleeyed.webapp;

import com.goggleeyed.webapp.model.Resume;
import com.goggleeyed.webapp.storage.SortedArrayStorage;
import com.goggleeyed.webapp.storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid1");
        final Resume r2 = new Resume();
        r2.setUuid("uuid2");
        final Resume r3 = new Resume();
        r3.setUuid("uuid3");
        final Resume r4 = new Resume();
        r4.setUuid("uuid4");
        final Resume r5 = new Resume();
        r5.setUuid("uuid5");
        final Resume r6 = new Resume();
        r6.setUuid("uuid6");

        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r2);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();

        System.out.println("Delete Resume " + r4.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();

        final Resume r7 = new Resume();
        r7.setUuid("uuid3");
        System.out.println("Update Resume " + r7.getUuid());
        ARRAY_STORAGE.update(r7);
        printAll();

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
