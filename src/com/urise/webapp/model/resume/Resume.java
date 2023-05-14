package com.urise.webapp.model.resume;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;

    private final Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "Parameter 'uuid' must not be null.");
        Objects.requireNonNull(fullName, "Parameter 'fullName' must not be null.");
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
        Objects.requireNonNull(fullName, "Parameter 'fullName' must not be null.");
        this.fullName = fullName;
    }

    public Contact getContact(ContactType type) {
        return contacts.get(type);
    }

    public Set<ContactType> getAllAddedContactTypes() {
        return contacts.keySet();
    }

    public void setContact(Contact contact) {
        Objects.requireNonNull(contact, "Parameter 'contact' must not be null.");
        contacts.put(contact.getContactType(), contact);
    }

    public void removeContact(ContactType type) {
        contacts.remove(type);
    }

    public Set<SectionType> getAllAddedSectionsTypes() {
        return sections.keySet();
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(Section section) {
        Objects.requireNonNull(section, "Parameter 'section' must not be null.");
        sections.put(section.sectionType, section);
    }

    public void removeSection(SectionType type) {
        sections.remove(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contacts.equals(resume.contacts)) return false;
        return sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contacts.hashCode();
        result = 31 * result + sections.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("UUID: %s FullName: %s", uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
