
package R31.R31.service;


import R31.R31.model.Score;
import R31.R31.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class ScoreService {
        @Autowired
    private ScoreRepository metodosCrud;
    
    public List<Score> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Score> getScore(int id){
        return metodosCrud.getScore(id);
    }
    
    public Score save(Score score){
         if(score.getIdScore()==null){
            return metodosCrud.save(score);
        }else{
            Optional<Score> evt=metodosCrud.getScore(score.getIdScore());
            if(evt.isEmpty()){
            return metodosCrud.save(score);
            }else{
                return score;
            }
        
        }
    }
     public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> e= metodosCrud.getScore(score.getIdScore());
            if(!e.isEmpty()){
                if(score.getMessageText()!=null){
                    e.get().setMessageText(score.getMessageText());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return score;
            }
        }else{
            return score;
        }
    }

    public boolean deleteScore(int scoreId) {
        Boolean aBoolean = getScore(scoreId).map(score-> {
            metodosCrud.delete(score);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

