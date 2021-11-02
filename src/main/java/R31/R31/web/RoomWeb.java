
package R31.R31.web;

import R31.R31.model.Rooms;
import R31.R31.service.RoomService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Room")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RoomWeb {
    
    @Autowired
    private RoomService servicios;
    @GetMapping("/all")
    public List <Rooms> getRooms(){
        return servicios.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Rooms> getRoom(@PathVariable("id") int id){
        return servicios.getRoom(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Rooms save(@RequestBody Rooms rooms){
        return servicios.save(rooms);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Rooms update(@RequestBody Rooms bike) {
        return servicios.update(bike);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int roomsId) {
        return servicios.deleteRoom(roomsId);
    } 
}
