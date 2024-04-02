package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.ResumeTestData;
import com.goggleeyed.webapp.exception.ExistStorageException;
import com.goggleeyed.webapp.exception.NotExistStorageException;
import com.goggleeyed.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("./storage");

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "name1";
    private static final Resume RESUME_1 = ResumeTestData.of(UUID_1, FULL_NAME_1);
    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "name2";
    private static final Resume RESUME_2 = ResumeTestData.of(UUID_2, FULL_NAME_2);
    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "name3";
    private static final Resume RESUME_3 = ResumeTestData.of(UUID_3, FULL_NAME_3);

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
        Resume resume_4 = ResumeTestData.of("uuid4", "name4");
        storage.save(resume_4);
        assertSize(4);
        assertGet(resume_4);
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(ResumeTestData.of(UUID_1, "dummy")));
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
        Resume newResume = ResumeTestData.of(UUID_1, "new name");
        storage.update(newResume);
        assertGet(newResume);
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(ResumeTestData.of("dummy", "dummy")));
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
    public void getAllSorted() {
        Assertions.assertEquals(Arrays.asList(RESUME_1, RESUME_2, RESUME_3), storage.getAllSorted());
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assertions.assertEquals(r, storage.get(r.getUuid()));
    }

}