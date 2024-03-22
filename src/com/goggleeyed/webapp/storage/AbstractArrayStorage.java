package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: Storage overflow");
            return;
        }
        int index = indexOf(r.getUuid());
        if (index >= 0) {
            System.out.println("Error: Resume " + r.getUuid() + " already exist");
            return;
        }
        insertElement(r, index);
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: Resume  " + uuid + "  not exist");
            return;
        }
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    public void update(Resume r) {
        int index = indexOf(r.getUuid());
        if (index < 0) {
            System.out.println("Error: Resume " + r.getUuid() + " not exist");
            return;
        }

        storage[index] = r;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: Resume  " + uuid + "  not exist");
            return null;
        }

        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int indexOf(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);
}
