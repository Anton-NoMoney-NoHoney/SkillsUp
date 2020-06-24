package ua.skillsup.practice.impl;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ua.skillsup.practice.ExampleEntity;
import ua.skillsup.practice.ExampleNetworkException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ExampleDaoTest {

    /*jUnit TEST*/
    @Mock
    private ExampleEntity entity;
    @Mock
    private ArrayList<ExampleEntity> ResultList;

    @Test
    public void shouldReturnTrueWhenAddComplited_Metod_store(){
        //GIVEN
        ExampleDao dao=new ExampleDao();
        //WHEN
        boolean result=dao.store(entity);
        //THEN
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenAddFailed_Metod_store(){
        //GIVEN
        ExampleDao dao=new ExampleDao();
        //WHEN
        dao.store(entity);
        boolean result=dao.store(entity);
        //THEN
        assertFalse(result);
    }

    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_store(){
        //GIVEN
        ExampleDao spy= Mockito.spy(new ExampleDao());
        //WHEN
        when(spy.store(any())).thenThrow(new ExampleNetworkException());
        //THEN
        spy.store(any());
    }

    @Test
    public void shouldReturnNULLWhenEntityListIsEmpty_Metod_findAll(){
        //GIVEN
        ExampleDao spy= Mockito.spy(new ExampleDao());
        //WHEN
        when(spy.findAll()).thenReturn(null);
        List<ExampleEntity> res=spy.findAll();
        //THEN
        assertNull(res);

    }

    @Test
    public void shouldReturnListWhenEntityListIsOK_Metod_findAll(){
        //GIVEN
        ExampleDao spy= Mockito.spy(new ExampleDao());
        //WHEN
        when(spy.findAll()).thenReturn(ResultList);
        List<ExampleEntity> res=spy.findAll();
        //THEN
        assertEquals(ResultList,res);
    }

    @Test(expected = ExampleNetworkException.class)
    public void shouldReturnExceptionWhenDaoThrowException_Metod_findAll(){
        //GIVEN
        ExampleDao spy= Mockito.spy(new ExampleDao());
        //WHEN
        when(spy.findAll()).thenThrow(new ExampleNetworkException());
        //THEN
        spy.findAll();

    }


}
