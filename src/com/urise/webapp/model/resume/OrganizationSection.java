package com.urise.webapp.model.resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {
    private final List<Organization> organizations = new ArrayList<>();

    public OrganizationSection(SectionType type) {
        super(type);
    }

    public List<Organization> getAllOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization organization) {
        Objects.requireNonNull(organization, "Parameter 'organization' must not be null.");
        organizations.add(organization);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;
        if (!super.equals(o)) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + organizations.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                ", sectionType=" + sectionType +
                '}';
    }
}
