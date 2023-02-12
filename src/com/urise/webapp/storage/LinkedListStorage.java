package com.urise.webapp.storage;

import java.util.LinkedList;

public class LinkedListStorage extends AbstractStorage {
    public LinkedListStorage() {
        super(new LinkedList<>());
    }
}
