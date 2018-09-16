package com.example.root.fatweightindice.business;

import org.junit.Test;

import static org.junit.Assert.*;

public class FWIComputationTest {

    private float fwi = 32.2f;
    private String message = "Too fat";
    private FWIComputation business = new FWIComputation();

    @Test
    public void createProfile() throws Exception{

        assertEquals(message, business.createProfile(67, 165, 35, 0).getComment());
    }

    @Test
    public void createProfile2() throws Exception{

        assertEquals(fwi, business.createProfile(67, 165, 35, 0).getFwi(), (float)0.1);
    }
}