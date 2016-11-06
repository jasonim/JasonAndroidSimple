package com.example.jason.mocksample;

/**
 * Created by jsson on 16/11/6.
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
public class ListTest {
    @Test
    public void test() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testNotMock() {

        // mock creation
        mock(ArrayList.class); //===>
        List<String> list = new ArrayList<>(); //==>

        // using mock object - it does not throw any "unexpected interaction" exception
        list.add("one");

        // selective, explicit, highly readable verification
        verify(list).add("one");
    }
}
