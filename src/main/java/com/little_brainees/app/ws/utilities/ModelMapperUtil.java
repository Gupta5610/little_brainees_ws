package com.little_brainees.app.ws.utilities;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {
	
	public static ModelMapperUtil shared = new ModelMapperUtil();
	private ModelMapper mapper;
	
	private ModelMapperUtil() {
		this.mapper = new ModelMapper();
	}
	
	public ModelMapper getMapper() {
		return this.mapper;
	}
}
