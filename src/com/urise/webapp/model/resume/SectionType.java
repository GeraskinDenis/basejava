package com.urise.webapp.model.resume;

public enum SectionType {
    PERSONAL("Личные качества", true),
    OBJECTIVE("Позиция", true),
    ACHIEVEMENT("Достижения", false),
    QUALIFICATIONS("Квалификация", false),
    EXPERIENCE("Опыт работы", false),
    EDUCATION("Образование", false);

    public final String TITLE;
    public final boolean IS_TEXT_SECTION;

    SectionType(String title, boolean isTextSection) {
        this.TITLE = title;
        this.IS_TEXT_SECTION = isTextSection;
    }
}
