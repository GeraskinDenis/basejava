package com.urise.webapp.model.resume;

import java.util.Objects;

public class TextSection extends Section {
    private String text;

    public TextSection(SectionType sectionType, String text) {
        super(sectionType);
        Objects.requireNonNull(text, "Parameter 'text' must not be null.");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Objects.requireNonNull(text, "Parameter 'text' must not be null.");
        if (text.isEmpty()) {
            throw new NullPointerException("Parameter 'text' must not be empty.");
        }
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;
        if (!super.equals(o)) return false;

        TextSection that = (TextSection) o;

        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "sectionType=" + sectionType +
                ", text='" + text + '\'' +
                '}';
    }
}
