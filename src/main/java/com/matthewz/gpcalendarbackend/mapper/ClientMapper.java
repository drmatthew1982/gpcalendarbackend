package com.matthewz.gpcalendarbackend.mapper;
import com.matthewz.gpcalendarbackend.clients.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "clientMapper")
public interface ClientMapper {
    List<Client> findClientsByUserId(@Param("user_id") String user_id);
    List<Client> findClientByClientIdNoAndUserId(@Param("user_id")String user_id, @Param("client_id_no") String client_id_no);
    void createClient(@Param("client")Client client);
    void updateClient(@Param("client")Client client);
    void createClientUserRelation(@Param("user_id")String user_id,@Param("client_sys_id")String client_sys_id);
}
