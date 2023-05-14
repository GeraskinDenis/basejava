package com.urise.webapp.model.resume;

public abstract class Section {
    protected final SectionType sectionType;

    protected Section(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;

        Section section = (Section) o;

        return sectionType == section.sectionType;
    }

    @Override
    public int hashCode() {
        return sectionType.hashCode();
    }
}