package ua.skillsup.practice.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skillsup.practice.ExampleNetworkException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(value = MockitoJUnitRunner.class)
public class ExampleServiceImplTest {

    private static final String NORMAL_TITLE = "TEST";
    private static final BigDecimal NORMAL_PRICE = BigDecimal.valueOf(30);
    private static final BigDecimal SMALL_PRICE = BigDecimal.ONE;
    private static final String SMALL_TITLE = "NO";
    private static final String LARGE_TITLE = "VeryVeryVeryVeryVeryVeryVeryVeryVeryLARGE_TITLE";
    private static final String NORMAL_TITLE_TO_STATISTIC = "TEST_1";
    private static final BigDecimal NORMAL_PRICE_TO_STATISTIC = BigDecimal.valueOf(40);
    private static final BigDecimal AVERAGE = BigDecimal.valueOf(2);

    private ExampleServiceImpl exampleService=new ExampleServiceImpl();


    @Mock
    ExampleServiceImpl service=spy(ExampleServiceImpl.class);

    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExampleNetworkExceptionWhenEntityListIsEmpty_Metod_addNewItem() throws ExampleNetworkException{
        //GIVEN
        //WHEN
        Mockito.doThrow(new ExampleNetworkException()).when(service).addNewItem(NORMAL_TITLE, NORMAL_PRICE);
        //THEN
        service.addNewItem(NORMAL_TITLE, NORMAL_PRICE);
    }

    @Test
    public void shouldIgnoreTitleWhenPriceIsSmall(){
        //GIVEN
        ExampleServiceImpl exampleService=new ExampleServiceImpl();
        //WHEN
        exampleService.addNewItem(NORMAL_TITLE, SMALL_PRICE);
        //THEN
        Assert.assertNull(exampleService.getStatistic());
    }

    @Test
    public void shouldAddTitleWhenNormalTitleAndPrice(){
        //GIVEN
        ExampleServiceImpl exampleService=new ExampleServiceImpl();
        //WHEN
        exampleService.addNewItem(NORMAL_TITLE, NORMAL_PRICE);
        //THEN
        Assert.assertNotNull(exampleService.getStatistic());
    }

    @Test
    public void shouldIgnoreTitleWhenTitleIsSmall(){
        //GIVEN
        //WHEN
        exampleService.addNewItem(SMALL_TITLE, NORMAL_PRICE);
        //THEN
        Assert.assertNull(exampleService.getStatistic());
    }

    @Test
    public void shouldIgnoreTitleWhenTitleIsLarge(){
        //GIVEN
        //WHEN
        exampleService.addNewItem(LARGE_TITLE, NORMAL_PRICE);
        //THEN
        Assert.assertNull(exampleService.getStatistic());
    }

    @Test
    public void shouldReturnStatisticWhenDataIssue(){
        //GIVEN
        exampleService.addNewItem(NORMAL_TITLE, NORMAL_PRICE);
        exampleService.addNewItem(NORMAL_TITLE_TO_STATISTIC, NORMAL_PRICE_TO_STATISTIC);
        BigDecimal testAverage=NORMAL_PRICE.add(NORMAL_PRICE_TO_STATISTIC).divide(AVERAGE);
        //WHEN
        Map<LocalDate, BigDecimal> statisticAverageCost=exampleService.getStatistic();
        BigDecimal average= BigDecimal.ZERO;
        for(Map.Entry<LocalDate, BigDecimal> e:statisticAverageCost.entrySet()) {
            average= e.getValue();
        }
        //THEN
        assertEquals(0,average.compareTo(testAverage));
    }

    @Test
    public void shouldReturnNULLWhenDataEmpty(){
        //GIVEN
        //WHEN
        Map<LocalDate, BigDecimal> statisticAverageCost=exampleService.getStatistic();
        //THEN
        assertNull(statisticAverageCost);
    }


}
