package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.organizations.Organization;
import com.matthewz.gpcalendarbackend.users.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "organizationMapper")
public interface OrganizationMapper {
    List<Organization> findOrgByCodeAndUserId(@Param("org")Organization organization);
    List<Organization> findOrgByUserId(@Param("org")Organization organization);
    void createOrganization(@Param("org")Organization organization);
}
