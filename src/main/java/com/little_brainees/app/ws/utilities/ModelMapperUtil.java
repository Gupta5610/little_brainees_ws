package com.little_brainees.app.ws.utilities;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {
	
	private static ModelMapperUtil shared = new ModelMapperUtil();
	private ModelMapper mapper;
	
	private ModelMapperUtil() {
		this.mapper = new ModelMapper();
	}
	
	public ModelMapper getMapper() {
		return this.mapper;
	}
	
	public static Object map(Object source,Class type) {
		return shared.mapper.map(source, type);
	}
}
