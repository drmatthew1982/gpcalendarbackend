package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.organizations.Organization;
import com.matthewz.gpcalendarbackend.users.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "orginzationMapper")
public interface OrganizationMapper {


    List<Organization> findOrgByCodeAndUserId(Organization organization);
    void createOrganizationMapper(Organization organization);
}
