
package R31.R31.repository;

import R31.R31.ClientInterface;
import R31.R31.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
     @Autowired
        private ClientInterface crud1;

    public List<Client> getAll(){
        return (List<Client>) crud1.findAll();
    }
    
    public Optional <Client> getClient(int id){
        return crud1.findById(id);
    }
    
    public Client save(Client client){
        return crud1.save(client);
    }
    public void delete(Client client){
        crud1.delete(client);
    }
    
}
