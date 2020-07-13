package ua.skillsup.practice.impl;

import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;

import java.math.BigDecimal;
import java.time.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ExampleServiceImpl implements ua.skillsup.practice.ExampleService  {

    private static final int AVERAGE = 2;
    private ExampleDaoImpl exampleDaoImpl =new ExampleDaoImpl();
    private HashSet uniqueNameToTitle=new HashSet();

    @Override
    public void addNewItem(String title, BigDecimal price) throws ExampleNetworkException {
        if (price.compareTo(BigDecimal.valueOf(15.00)) == 1) {
            if (title.length() >= 3 && title.length() <= 20 && !uniqueNameToTitle.contains(title)) {
                saveExamplEntity( title,  price);
            }
        }
    }

    @Override
    public Map<LocalDate, BigDecimal> getStatistic() {
        Map<LocalDate, BigDecimal> statisticAverageCost=new HashMap<>();
        try{
        List<ExampleEntity> exampleDaosList= exampleDaoImpl.findAll();
            for(ExampleEntity entity: exampleDaosList){
                LocalDate loc= Instant.ofEpochMilli(entity.getDateIn().getEpochSecond()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
                if(!statisticAverageCost.containsKey(loc)){
                    statisticAverageCost.put(loc,entity.getPrice());
                }else{
                    BigDecimal oldAveragePrices=statisticAverageCost.get(loc);
                    BigDecimal newAveragePrices= entity.getPrice().add(oldAveragePrices).divide(BigDecimal.valueOf(AVERAGE));
                    statisticAverageCost.put(loc,newAveragePrices);
                }
            }
        }catch (Exception ex){
            return null;
        }
        if(!statisticAverageCost.isEmpty()){
            return statisticAverageCost;
        }else {
            return null;
        }
    }

    private void saveExamplEntity(String title, BigDecimal price ){
        ExampleEntity entity = new ExampleEntity();
        entity.setId(Clock.systemDefaultZone().instant().getEpochSecond());
        entity.setDateIn(Clock.systemDefaultZone().instant());
        entity.setPrice(price.setScale(2));
        entity.setTitle(title);
        exampleDaoImpl.store(entity);
        uniqueNameToTitle.add(title);
    }
}
