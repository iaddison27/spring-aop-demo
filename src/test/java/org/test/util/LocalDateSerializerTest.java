package org.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringWriter;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LocalDateSerializerTest {

	private ObjectMapper mapper;

	@Before
	public void setup() {
		mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("LocalDateSerializerModule");
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		mapper.registerModule(module);
	}
	
	@Test
	public void validFormat() {
		
		try {
			LocalDate input = LocalDate.of(2015, 6, 7);
			
			StringWriter s = new StringWriter();
			mapper.writeValue(s, input);
			
			assertEquals("\"2015-06-07\"", s.toString());
		} catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void nullParameter() {
		
		try {
			LocalDate input = null;
			StringWriter s = new StringWriter();
			mapper.writeValue(s, input);
			assertEquals("null", s.toString());
		} catch(Exception e) {
			fail();
		}
	}
}
