package com.company.loaf.routinescheduler;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonTests {

    @Test
    public void jsonTest_SingleRoutineJson_ReturnsValidArray(){
        //"{\"data\":{\"translations\":[{\"translatedText\":\"Bonjour tout le monde\"}]}}";
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"test\",\n" +
                "    \"interval\": 5,\n" +
                "    \"savedDate\": \"0000\"\n" +
                "  }\n" +
                "]";
        Routine[] arr = JSONConverter.jsonToRoutines(json);

        Assert.assertTrue(arr.length == 1);

        Routine r = arr[0];

        Assert.assertTrue(r.getName().equals("test"));
        Assert.assertTrue(r.getInterval() == 5);
        Assert.assertTrue(r.getSavedDate().equals("0000"));
    }

    @Test(expected = NullPointerException.class)
    public void jsonTest_EmptyJson_ReturnsEmptyArray(){
        Routine[] arr = JSONConverter.jsonToRoutines("");
        int len = arr.length;
    }

}
