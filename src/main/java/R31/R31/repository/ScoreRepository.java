
package R31.R31.repository;

import R31.R31.ScoreInterface;
import R31.R31.model.Score;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreRepository {
    @Autowired
        private ScoreInterface crud5;

    public List<Score> getAll(){
        return (List<Score>) crud5.findAll();
    }
    
    public Optional <Score> getScore(int id){
        return crud5.findById(id);
    }
    
    public Score save(Score score){
        return crud5.save(score);
    }
    public void delete(Score score){
        crud5.delete(score);
    }
}
