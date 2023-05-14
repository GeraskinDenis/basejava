package com.urise.webapp.model.resume;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String title;
    private final LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public Period(String title, LocalDate startDate) {
        Objects.requireNonNull(title, "Parameter 'title' must not be null.");
        Objects.requireNonNull(startDate, "Parameter 'starDate' must not be null.");
        this.title = title;
        this.startDate = startDate;
    }

    public Period(String title, LocalDate startDate, LocalDate endDate) {
        this(title, startDate);
        Objects.requireNonNull(endDate, "Parameter 'endDate' must not be null.");
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;

        Period period = (Period) o;

        if (!title.equals(period.title)) return false;
        if (!startDate.equals(period.startDate)) return false;
        if (!Objects.equals(endDate, period.endDate)) return false;
        return Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", start=" + startDate +
                ", end=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }
}
