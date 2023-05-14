package com.urise.webapp.model.resume;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private String title;
    private LocalDate start;
    private LocalDate end;
    private String description;

    public Period(String title, LocalDate start) {
        this.title = title;
        this.start = start;
    }

    public Period(String title, LocalDate start, LocalDate end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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

        if (!Objects.equals(title, period.title)) return false;
        if (!Objects.equals(start, period.start)) return false;
        if (!Objects.equals(end, period.end)) return false;
        return Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", description='" + description + '\'' +
                '}';
    }
}
