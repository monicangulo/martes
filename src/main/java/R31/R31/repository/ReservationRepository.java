
package R31.R31.repository;

import R31.R31.ReservationInterface;
import R31.R31.model.Client;
import R31.R31.model.Reservation;
import R31.R31.model.custom.CountClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    @Autowired
        private ReservationInterface crud4;

    public List<Reservation> getAll(){
        return (List<Reservation>) crud4.findAll();
    }
    
    public Optional <Reservation> getReservation(int id){
        return crud4.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return crud4.save(reservation);
    }
    
    public void delete(Reservation reservation){
        crud4.delete(reservation);
    }
    
    public List<Reservation> getReservationByStatus(String status){
    return crud4.findAllByStatus(status);
    }

    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
        return crud4.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    }

    public List<CountClient> getTopClients(){
        List<CountClient> res=new ArrayList<>();

        List<Object[]> report=crud4.countTotalReservationByClient();
        for(int i=0;i<report.size();i++){
            
            res.add(new CountClient((Long) report.get(i)[1],(Client)report.get(i)[0] ));
        }
        return res;
    }
}
