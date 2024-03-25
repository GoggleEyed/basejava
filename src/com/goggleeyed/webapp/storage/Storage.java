package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    int size();

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();
}
