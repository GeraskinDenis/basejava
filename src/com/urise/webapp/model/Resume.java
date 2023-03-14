package com.urise.webapp.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * 1 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    public static final Comparator<Resume> comparatorByNameUuid = new ComparatorByNameUuid();

    private final String uuid;
    private String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return String.format("UUID: %s FullName: %s", uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    private static class ComparatorByNameUuid implements Comparator<Resume> {

        @Override
        public int compare(Resume r1, Resume r2) {
            int c = r1.fullName.compareTo(r2.fullName);
            return (c != 0) ? c : r1.uuid.compareTo(r2.uuid);
        }
    }
}
