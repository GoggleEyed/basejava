package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = indexOf(r.getUuid());
        if (index == -1) {
            System.out.println("Error: Resume " + r.getUuid() + " not exist");
            return;
        }

        storage[index] = r;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: Storage overflow");
            return;
        }
        if (indexOf(r.getUuid()) != -1) {
            System.out.println("Error: Resume " + r.getUuid() + " already exist");
            return;
        }

        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("Error: Resume  " + uuid + "  not exist");
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("Error: Resume  " + uuid + "  not exist");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
