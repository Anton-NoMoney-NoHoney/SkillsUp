package ua.skillsup.practice.impl;

import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;

import java.math.BigDecimal;
import java.time.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ExampleService implements ua.skillsup.practice.ExampleService {

    protected static final BigDecimal AVERAGE = BigDecimal.valueOf(2);
    protected ExampleDao exampleDao=new ExampleDao();
    protected HashSet uniqueNameToTitle=new HashSet();

    @Override
    public void addNewItem(String title, BigDecimal price) throws ExampleNetworkException {
        if (price.compareTo(BigDecimal.valueOf(15.00)) == 1) {
            if (title.length() >= 3 && title.length() <= 20 && !uniqueNameToTitle.contains(title)) {
                ExampleEntity entity = new ExampleEntity();
                entity.setId(Clock.systemDefaultZone().instant().getEpochSecond());
                entity.setDateIn(Clock.systemDefaultZone().instant());
                entity.setPrice(price.setScale(2));
                entity.setTitle(title);
                if(exampleDao.store(entity)){
                    uniqueNameToTitle.add(title);
                }
            }
        }
    }

    @Override
    public Map<LocalDate, BigDecimal> getStatistic() {
        Map<LocalDate, BigDecimal> statisticAverageCost=new HashMap<>();
        List<ExampleEntity> exampleDaosList= exampleDao.findAll();
        try {
            for (ExampleEntity entity : exampleDaosList) {
                LocalDate loc = Instant.ofEpochMilli(entity.getDateIn().getEpochSecond() * 1000).atZone(ZoneId.systemDefault()).toLocalDate();
                if (!statisticAverageCost.containsKey(loc)) {
                    statisticAverageCost.put(loc, entity.getPrice());
                } else {
                    BigDecimal oldAveragePrices = statisticAverageCost.get(loc);
                    BigDecimal newAveragePrices = entity.getPrice().add(oldAveragePrices).divide(AVERAGE);
                    statisticAverageCost.put(loc, newAveragePrices);
                }
            }
        }catch (NullPointerException ex){
            return null;
        }

        if(!statisticAverageCost.isEmpty()){
            return statisticAverageCost;
        }else {
            return null;
        }
    }
}
