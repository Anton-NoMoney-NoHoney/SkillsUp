package ua.skillsup.practice.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ExampleDaoImplTest {

    /*jUnit TEST*/
    @Mock
    private ExampleEntity entity;
    @Mock
    private ArrayList<ExampleEntity> ResultList;

    ExampleDaoImpl dao;
    @Before public void initialize() {
         dao=new ExampleDaoImpl();
    }


    @Test
    public void shouldReturnTrueWhenAddComplited_Metod_store(){
        //GIVEN
        //WHEN
        ExampleEntity entityTest =new ExampleEntity();
        entityTest.setTitle("test");
        boolean result=dao.store(entityTest);
        //THEN
        assertTrue(result);
    }

//    bcghfdbnm
    @Test
    public void shouldNotStoreObjectWhichIsAlreadyPresentInDb(){
        //GIVEN
        //WHEN
        ExampleEntity entityTest =new ExampleEntity();
        entityTest.setTitle("test");
        dao.store(entityTest);
        boolean result= dao.store(entityTest);
        //THEN
        assertFalse(result);
    }



    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_store(){
        //GIVEN
        //WHEN
        //THEN
        dao.store(null);
    }


    @Test
    public void shouldReturnListWhenEntityListIsOK_Metod_findAll(){
        //GIVEN
        //WHEN
        ExampleEntity entityTest =new ExampleEntity();
        entityTest.setTitle("test");
        dao.store(entityTest);
        List<ExampleEntity> res=dao.findAll();
        //THEN
        assertEquals(res.size(),1);
    }

    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_findAll(){
        //GIVEN
        //WHEN
        //THEN
        dao.findAll();
    }




}
