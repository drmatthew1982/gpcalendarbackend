package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.organizations.Organization;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "organizationMapper")
public interface OrganizationMapper {
    List<Organization> findOrgByCodeAndUserId(@Param("org")Organization organization);
    List<Organization> findOrgByUserId(@Param("org")Organization organization);
    void createOrganization(@Param("org")Organization organization);
    void updateOrganization(@Param("org")Organization organization);
    void createOrgUserRelation(@Param("user_id")String userid,@Param("org_id")String org_id);
}
