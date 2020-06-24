package ua.skillsup.practice.impl;

import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ExampleDao implements ua.skillsup.practice.ExampleDao {


    private HashSet<ExampleEntity> entityListToSaveDao=new HashSet<>();

    @Override
    public boolean store(ExampleEntity entity) throws ExampleNetworkException {
        if(!entityListToSaveDao.contains(entity)){
            try{
                entityListToSaveDao.add(entity);
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
        if(entityListToSaveDao.isEmpty()){
            return null;
        }else{
            try {
                return (List<ExampleEntity>) new ArrayList<ExampleEntity>(entityListToSaveDao);
            }catch (Exception ex){
                throw new ExampleNetworkException();
            }
        }
    }
}
