package com.matthewz.gpcalendarbackend.organizations;

import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
import com.matthewz.gpcalendarbackend.mapper.OrganisationMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrganisationController {
    @Autowired
    private OrganisationMapper orginazationMappper;
    @RequestMapping("/findorgbyuserid")
    public ResponseEntity<Object> findOrgsByUserId(Organisation organisation, HttpServletResponse response) {
        List<Organisation> orgs = orginazationMappper.findOrgByUserId(organisation);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(orgs,HttpStatus.OK);
    }
    @RequestMapping("/createorg")
    public ResponseEntity<Object> createorg(@RequestBody Organisation organisation, HttpServletResponse response) {
        List<Organisation> orgs = orginazationMappper.findOrgByCodeAndUserId(organisation);
        if(orgs.size()==0) {
            orginazationMappper.createOrganisation(organisation);
            List<Organisation> updatedOrgs = orginazationMappper.findOrgByCodeAndUserId(organisation);
            orginazationMappper.createOrgUserRelation(organisation.getCreated_user_id(),updatedOrgs.get(0).getId());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.DUPLICATE.getCode(),MeaasgeTextEnum.DUPLICATE.getText()),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/updateorg")
    public ResponseEntity<Object> updateorg(@RequestBody Organisation organisation, HttpServletResponse response) {
        orginazationMappper.updateOrganisation(organisation);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.UPDATE_SUCCESS.getCode(),MeaasgeTextEnum.UPDATE_SUCCESS.getText()),HttpStatus.OK);
    }
}
