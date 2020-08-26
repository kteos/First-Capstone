package com.techelevator;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.menuDisplayer;



public class menuDisplayerTest {
	private menuDisplayer target;
@Before
public void setUp() {
	target = new menuDisplayer();
}
	
@Test
public void testAFile() {
	File file =new File("cateringsystem.csv");
	boolean isItFile =file.exists();
	Assert.assertTrue(isItFile);
	
}

}
