package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.exception.ExistStorageException;
import com.goggleeyed.webapp.exception.NotExistStorageException;
import com.goggleeyed.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        Resume resume_4 = new Resume("uuid4");
        storage.save(resume_4);
        assertSize(4);
        assertGet(resume_4);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void update() {
        Resume new_resume_1 = new Resume(UUID_1);
        storage.update(new_resume_1);
        assertGet(new_resume_1);
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }


    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void getAll() {
        Resume[] resumes =  storage.getAll();
        Arrays.sort(resumes);
        Assertions.assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, resumes);
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(r, storage.get(r.getUuid()));
    }

}