package com.matthewz.gpcalendarbackend.organizations;

import com.matthewz.gpcalendarbackend.mapper.OrganizationMapper;
import com.matthewz.gpcalendarbackend.mapper.UserMapper;
import com.matthewz.gpcalendarbackend.users.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrganizationController {
    @Autowired
    private OrganizationMapper orginazationMappper;

    @RequestMapping("/createorg")
    public ResponseEntity<Object> createorg(Organization organization, HttpServletResponse response) {
        List<Organization> orgs = orginazationMappper.findOrgByCodeAndUserId(organization);
        if(orgs.size()==0) {
            orginazationMappper.createOrganizationMapper(organization);
            response.setHeader("Access-Control-Allow-Origin", "*");
            return new ResponseEntity<Object>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }
    }
}
