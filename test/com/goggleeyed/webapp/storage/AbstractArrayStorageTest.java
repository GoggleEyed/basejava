package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.exception.StorageException;
import com.goggleeyed.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        StorageException exception = Assertions.assertThrows(StorageException.class, () -> {
            try {
                for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume(UUID.randomUUID().toString(), "name" + i));
                }
            } catch (StorageException e) {
                Assertions.fail("Exception is thrown before overflowing");
            }
            storage.save(new Resume(UUID.randomUUID().toString(), "dummy"));
        });
        Assertions.assertEquals("Storage overflow", exception.getMessage());
    }
}