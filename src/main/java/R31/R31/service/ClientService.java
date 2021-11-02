
package R31.R31.service;

import R31.R31.model.Client;
import R31.R31.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository metodosCrud;
    
    public List<Client> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Client> getClient(int id){
        return metodosCrud.getClient(id);
    }
    
    public Client save(Client client){
         if(client.getIdClient()==null){
            return metodosCrud.save(client);
        }else{
            Optional<Client> evt=metodosCrud.getClient(client.getIdClient());
            if(evt.isEmpty()){
            return metodosCrud.save(client);
            }else{
                return client;
            }
        
        }
    }
    
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> e= metodosCrud.getClient(client.getIdClient());
            if(!e.isEmpty()){
                if(client.getName()!=null){
                    e.get().setName(client.getName());
                }
                if(client.getAge()!=null){
                    e.get().setAge(client.getAge());
                }
                if(client.getPassword()!=null){
                    e.get().setPassword(client.getPassword());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean deleteClient(int clientId) {
        Boolean aBoolean = getClient(clientId).map(client -> {
            metodosCrud.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
   
}
