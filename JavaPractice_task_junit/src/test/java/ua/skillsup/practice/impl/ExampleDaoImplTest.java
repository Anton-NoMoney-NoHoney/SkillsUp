package ua.skillsup.practice.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;
import ua.skillsup.practice.ExampleService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ExampleDaoImplTest {

    /*jUnit TEST*/
    @Mock
    private ExampleEntity entity;
    @Mock
    private ArrayList<ExampleEntity> ResultList;

    ExampleDaoImpl spy;
    @Before public void initialize() {

         spy=Mockito.spy(new ExampleDaoImpl());
    }


    @Test
    public void shouldReturnTrueWhenAddComplited_Metod_store(){
        //GIVEN
        ExampleDaoImpl dao=new ExampleDaoImpl();
        //WHEN
        boolean result=dao.store(entity);
        //THEN
        assertTrue(result);
    }

    @Test
    public void shouldNotStoreObjectWhichIsAlreadyPresentInDb(){
        //GIVEN

        //WHEN
        when(spy.store(any())).thenReturn(false);
        boolean result= spy.store(entity);
        //THEN
        assertFalse(result);
    }



    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_store(){
        //GIVEN
        //WHEN
        when(spy.store(any())).thenThrow(new ExampleNetworkException());
        //THEN
        spy.store(any());
    }


    @Test
    public void shouldReturnListWhenEntityListIsOK_Metod_findAll(){
        //GIVEN
        ExampleDaoImpl dao=new ExampleDaoImpl();
        //WHEN
        dao.store(new ExampleEntity());
        List<ExampleEntity> res=dao.findAll();
        //THEN
        assertEquals(ResultList,null);
        assertEquals(res.size(),1);
    }

    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_findAll(){
        //GIVEN
        ExampleDaoImpl dao=new ExampleDaoImpl();
        //WHEN
        //THEN
        dao.findAll();

    }




}
