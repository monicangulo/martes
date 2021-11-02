
package R31.R31.service;

import R31.R31.model.Rooms;
import R31.R31.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class RoomService {
    @Autowired
    private RoomRepository metodosCrud;
    
    public List<Rooms> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Rooms> getRoom(int id){
        return metodosCrud.getRoom(id);
    }
    
    public Rooms save(Rooms rooms){
         if(rooms.getId()==null){
            return metodosCrud.save(rooms);
        }else{
            Optional<Rooms> evt=metodosCrud.getRoom(rooms.getId());
            if(evt.isEmpty()){
            return metodosCrud.save(rooms);
            }else{
                return rooms;
            }
        
        }
    }
    
    public Rooms update(Rooms rooms){
        if(rooms.getId()!=null){
            Optional<Rooms> e=metodosCrud.getRoom(rooms.getId());
            if(!e.isEmpty()){
                if(rooms.getName()!=null){
                    e.get().setName(rooms.getName());
                }
                if(rooms.getHotel()!=null){
                    e.get().setHotel(rooms.getHotel());
                }
                if(rooms.getStars()!=null){
                    e.get().setStars(rooms.getStars());
                }
                if(rooms.getDescription()!=null){
                    e.get().setDescription(rooms.getDescription());
                }
                if(rooms.getCategory()!=null){
                    e.get().setCategory(rooms.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return rooms;
            }
        }else{
            return rooms;
        }
    }


    public boolean deleteRoom(int roomId) {
        Boolean aBoolean = getRoom(roomId).map(rooms -> {
            metodosCrud.delete(rooms);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
