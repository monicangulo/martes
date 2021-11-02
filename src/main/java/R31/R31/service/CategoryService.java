
package R31.R31.service;

import R31.R31.model.Category;
import R31.R31.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository metodosCrud;
    
    public List<Category> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Category> getCategory(int id){
        return metodosCrud.getCategory(id);
    }
    
    public Category save(Category category){
        if(category.getId()==null){
            return metodosCrud.save(category);
        }else{
            Optional<Category> evt=metodosCrud.getCategory(category.getId());
            if(evt.isEmpty()){
            return metodosCrud.save(category);
            }else{
                return category;
            }
        
        }
    }
    
    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=metodosCrud.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return category;
    }
    public boolean deletecategoria(int categoriaId){
        Boolean d=getCategory(categoriaId).map(category -> {
            metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
