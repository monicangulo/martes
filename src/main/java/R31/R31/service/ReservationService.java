
package R31.R31.service;

import R31.R31.model.Reservation;
import R31.R31.model.custom.CountClient;
import R31.R31.model.custom.StatusAmount;
import R31.R31.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class ReservationService {
    @Autowired
    private ReservationRepository metodosCrud;
    
    public List<Reservation> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return metodosCrud.getReservation(id);
    }
    
    public Reservation save(Reservation reservation){
         if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservation> evt=metodosCrud.getReservation(reservation.getIdReservation());
            if(evt.isEmpty()){
            return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        
        }
    }
    
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
     public List<CountClient> getTopClient(){
        return metodosCrud.getTopClients();
    }

    public StatusAmount getStatusReport(){
        List<Reservation> completed=metodosCrud.getReservationByStatus("completed");
        List<Reservation> cancelled=metodosCrud.getReservationByStatus("cancelled");

        StatusAmount descAmt=new StatusAmount(completed.size(),cancelled.size());
        return descAmt;
    }
    public List<Reservation> getReservationPeriod(String d1, String d2){

        // yyyy-MM-dd
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try {
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        if(dateOne.before(dateTwo)){
            return metodosCrud.getReservationPeriod(dateOne,dateTwo);
        }else{
            return new ArrayList<>();
        }
    }
}
