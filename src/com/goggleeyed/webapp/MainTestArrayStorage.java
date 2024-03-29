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
        final Resume r1 = new Resume("uuid1", "name1");
        final Resume r2 = new Resume("uuid2", "name2");
        final Resume r3 = new Resume("uuid3", "name3");
        final Resume r4 = new Resume("uuid4", "name4");
        final Resume r5 = new Resume("uuid5", "name5");
        final Resume r6 = new Resume("uuid6", "name6");

        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r2);


        printAll();
        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

//        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();

        System.out.println("Delete Resume " + r4.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());
        printAll();

        final Resume r7 = new Resume("uuid3", "new name");
        System.out.println("Update Resume " + r7.getUuid());
        ARRAY_STORAGE.update(r7);
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("Get All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
        System.out.println();
    }
}
