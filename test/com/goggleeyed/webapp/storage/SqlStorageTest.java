package com.goggleeyed.webapp.storage;

import com.goggleeyed.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}