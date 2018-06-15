package ru.model;

public enum Contacts {

    TELEPHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STATCKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    Contacts(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
