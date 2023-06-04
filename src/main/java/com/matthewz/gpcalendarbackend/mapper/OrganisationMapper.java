package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.organizations.Organisation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "organizationMapper")
public interface OrganisationMapper {
    List<Organisation> findOrgByCodeAndUserId(@Param("org") Organisation organization);
    List<Organisation> findOrgByUserId(@Param("org") Organisation organization);
    void createOrganisation(@Param("org") Organisation organization);
    void updateOrganisation(@Param("org") Organisation organization);
    void createOrgUserRelation(@Param("user_id")String userid,@Param("org_id")String org_id);
}
