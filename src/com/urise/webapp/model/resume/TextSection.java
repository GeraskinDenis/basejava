package com.urise.webapp.model.resume;

import java.util.Objects;

public class TextSection extends Section {
    private String text;

    public TextSection(SectionType sectionType, String text) {
        super(sectionType);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;
        if (!super.equals(o)) return false;

        TextSection that = (TextSection) o;

        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

//    @Override
//    public String toString() {
//        return String.format("SectionType: %s Text: %s", sectionType, text);
//    }

    @Override
    public String toString() {
        return "TextSection{" +
                "sectionType=" + sectionType +
                ", text='" + text + '\'' +
                '}';
    }
}
