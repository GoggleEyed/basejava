package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            System.out.println("Error: Resume  " + uuid + "  not exist");
            return null;
        }

        return storage[index];
    }

    protected abstract int indexOf(String uuid);

    public int size() {
        return size;
    }
}
