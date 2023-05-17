package com.urise.webapp.model.resume;

public enum ContactType {
    MOBILEPHONE("Мобильный телефон"),
    WORKPHONE("Рабочий телефон"),
    SKYPE("Skype"),
    EMAIL("Электронная почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    public final String NAME;

    ContactType(String name) {
        this.NAME = name;
    }
}
