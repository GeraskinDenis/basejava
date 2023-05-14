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

    public Contact getContact(ContactType type) {
        return contacts.get(type);
    }

    public Set<ContactType> getAllAddedContactTypes() {
        return contacts.keySet();
    }

    public void setContact(Contact contact) {
        contacts.put(contact.getContactType(), contact);
    }

    public void removeContact(ContactType type) {
        contacts.remove(type);
    }

    public Set<SectionType> getAllAddedAddedSectionsTypes() {
        return sections.keySet();
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(Section section) {
        sections.put(section.sectionType, section);
    }

    public void removeSection(SectionType type) {
        sections.remove(type);
    }

    // TODO hashcode() equals()

    @Override
    public String toString() {
        return String.format("UUID: %s FullName: %s", uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
