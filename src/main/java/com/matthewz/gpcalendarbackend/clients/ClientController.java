package com.matthewz.gpcalendarbackend.clients;

import com.matthewz.gpcalendarbackend.common.Massage;
import com.matthewz.gpcalendarbackend.common.MeaasgeTextEnum;
import com.matthewz.gpcalendarbackend.mapper.ClientMapper;
import com.matthewz.gpcalendarbackend.organizations.Organisation;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {
    @Autowired
    private ClientMapper clientonMapper;
    @RequestMapping("/findclientsbyuserid")
    public ResponseEntity<Object> findClientsByUserId(String user_id, HttpServletResponse response) {
        List<Client> clients = clientonMapper.findClientsByUserId(user_id);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(clients,HttpStatus.OK);
    }
    @RequestMapping("/createclient")
    public ResponseEntity<Object> createoclient(@RequestBody Client client, HttpServletResponse response) {
        List<Client> clients = clientonMapper.findClientByClientIdNoAndUserId(client.getClient_id_no(),client.getCreated_user_id());
        if(clients.size()==0) {
            clientonMapper.createClient(client);
            List<Client> updatedclients = clientonMapper.findClientByClientIdNoAndUserId(client.getClient_id_no(),client.getCreated_user_id());;
            clientonMapper.createClientUserRelation(client.getCreated_user_id(),updatedclients.get(0).getId());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.CREATE_SUCCESS.getCode(),MeaasgeTextEnum.CREATE_SUCCESS.getText()),HttpStatus.OK);
        }else{
            return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.DUPLICATE.getCode(),MeaasgeTextEnum.DUPLICATE.getText()),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/updateclient")
    public ResponseEntity<Object> updateclient(@RequestBody Client client, HttpServletResponse response) {
        clientonMapper.updateClient(client);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<Object>(new Massage(MeaasgeTextEnum.UPDATE_SUCCESS.getCode(),MeaasgeTextEnum.UPDATE_SUCCESS.getText()),HttpStatus.OK);
    }
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }
}
