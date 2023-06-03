package com.matthewz.gpcalendarbackend.organizations;

import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
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
    @RequestMapping("/findorgbyuserid")
    public ResponseEntity<Object> findOrgsByUserId(Organization organization, HttpServletResponse response) {
        List<Organization> orgs = orginazationMappper.findOrgByUserId(organization);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(orgs,HttpStatus.OK);
    }
    @RequestMapping("/createorg")
    public ResponseEntity<Object> createorg(Organization organization, HttpServletResponse response) {
        List<Organization> orgs = orginazationMappper.findOrgByCodeAndUserId(organization);
        if(orgs.size()==0) {
            orginazationMappper.createOrganization(organization);
            response.setHeader("Access-Control-Allow-Origin", "*");
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.DUPLICATE.getCode(),MeaasgeTextEnum.DUPLICATE.getText()),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/updateorg")
    public ResponseEntity<Object> updateorg(Organization organization, HttpServletResponse response) {
        orginazationMappper.updateOrganization(organization);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.UPDATE_SUCCESS.getCode(),MeaasgeTextEnum.UPDATE_SUCCESS.getText()),HttpStatus.OK);
    }
}
