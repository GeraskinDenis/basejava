package com.urise.webapp.model.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> list = new ArrayList<>();

    public ListSection(SectionType section) {
        super(section);
    }

    public List<String> getAllElements() {
        return list;
    }

    public void addElement(String text) {
        Objects.requireNonNull(text, "The text must can be null.");
        list.add(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        if (!super.equals(o)) return false;
        ListSection that = (ListSection) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "sectionType=" + sectionType +
                ", list=" + list +
                '}';
    }
}