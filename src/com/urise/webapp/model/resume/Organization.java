package com.urise.webapp.model.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String title;
    private String website;
    private final List<Period> periods = new ArrayList<>();

    public Organization(String title) {
        Objects.requireNonNull(title, "Parameter 'title' must not be null.");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        Objects.requireNonNull(website, "Parameter 'website' must not be null.");
        this.website = website;
    }

    public List<Period> getAllPeriods() {
        return periods;
    }

    public void setPeriod(Period period) {
        Objects.requireNonNull(period, "Parameter 'period' must not be null.");
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!Objects.equals(website, that.website)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", website='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }
}