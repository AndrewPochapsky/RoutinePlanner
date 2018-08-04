package com.company.loaf.routinescheduler;

import com.company.loaf.routinescheduler.Utils.JSONUtils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonTests {

    @Test
    public void jsonToRoutines_SingleRoutineJson_ReturnsValidArray(){
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"test\",\n" +
                "    \"interval\": 5,\n" +
                "    \"savedDate\": \"0000\"\n" +
                "  }\n" +
                "]";
        Routine[] arr = JSONUtils.jsonToRoutines(json);

        Assert.assertTrue(arr.length == 1);

        Routine r = arr[0];

        Assert.assertEquals("test", r.getName());
        Assert.assertEquals(5, r.getInterval());
        Assert.assertEquals("0000", r.getSavedDate());
    }

    @Test
    public void routinesToJson_SingleRoutineJson_ReturnsValidJson(){
        String json = "[\n" +
                "  {\n" +
                "    \"name\": \"test\",\n" +
                "    \"interval\": 5,\n" +
                "    \"savedDate\": \"0000\"\n" +
                "  }\n" +
                "]";
        json = json.replaceAll("[\r\n]+", " ");
        json = json.replaceAll(" ", "");
        Routine[] routines = {new Routine("test", 5, "0000")};
        String returnedJson = JSONUtils.routinesToJson(routines);
        Assert.assertEquals(json, returnedJson);
    }

    @Test
    public void jsonToRoutines_EmptyJson_ReturnsEmptyArray(){
        Routine[] arr = JSONUtils.jsonToRoutines("");
        Assert.assertNull(arr);
    }

}
