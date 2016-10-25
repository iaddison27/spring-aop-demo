package org.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LocalDateDeserializerTest {

	private ObjectMapper mapper;
	
	@Before
	public void setup() {
		mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("JsonDateDeserializerModule");
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		mapper.registerModule(module);
	}
	
	@Test
	public void validFormat() {
		
		try {
			LocalDate d = mapper.readValue("\"2015-06-07\"", LocalDate.class);
			LocalDate expected = LocalDate.of(2015, 6, 7);
			
			assertEquals(expected, d);
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void nullParameter() {
		
		try {
			LocalDate d = mapper.readValue("null", LocalDate.class);
			assertNull(d);
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test(expected=DateTimeParseException.class)
	public void invalidFormat() {
		try {
			mapper.readValue("\"aa15-06-07\"", LocalDate.class);
		} catch (IOException e) {
			fail();
		}
	}
}
