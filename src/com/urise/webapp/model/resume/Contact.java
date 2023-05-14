package com.urise.webapp.model.resume;

import java.util.Objects;

public class Contact {
    private final ContactType contactType;
    private String contact;
    private String comment;

    public Contact(ContactType contactType, String contact) {
        this.contactType = contactType;
        this.contact = contact;
    }

    public Contact(ContactType contactType, String contact, String comment) {
        this(contactType, contact);
        this.comment = comment;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact1 = (Contact) o;

        if (contactType != contact1.contactType) return false;
        if (!contact.equals(contact1.contact)) return false;
        return Objects.equals(comment, contact1.comment);
    }

    @Override
    public int hashCode() {
        int result = contactType.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("ContactType: %s Contact: %s Comment: %s",
                contactType, contact, (Objects.isNull(comment) || comment.isEmpty() ? "" : comment));
    }
}
