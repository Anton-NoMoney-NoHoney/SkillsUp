package ua.skillsup.practice.impl;

import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;

import java.util.ArrayList;
import java.util.List;

public class ExampleDaoImpl implements ua.skillsup.practice.ExampleDao {


    private List<ExampleEntity> entities =new ArrayList<>();

    @Override
    public boolean store(ExampleEntity entity) throws ExampleNetworkException {
        boolean save=true;
        for(ExampleEntity iter: entities){
            if(iter.getTitle().equals(entity.getTitle())){
                save=false;
                break;
            }
        }
        if(save){
            try{
                entities.add(entity);
            }catch (Exception ex){
                throw new ExampleNetworkException();
            }
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ExampleEntity> findAll() throws ExampleNetworkException {

        try {
            return (List<ExampleEntity>) new ArrayList<ExampleEntity>(entities);
        }catch (Exception ex){
            throw new ExampleNetworkException();
        }
    }
}
