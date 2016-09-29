package com.epam.jmp.configuration;

import static com.epam.jmp.controller.ControllerConstants.API_BASE_URL;
import static com.epam.jmp.controller.ControllerConstants.SLASH;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

public class UrlBasedContentNegotiationStrategy implements ContentNegotiationStrategy {
	
	@Override
	public List<MediaType> resolveMediaTypes(NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException {
		HttpServletRequest request = webRequest.getNativeRequest(javax.servlet.http.HttpServletRequest.class);
		if (!isAcceptableURI(request)) {
			return Collections.singletonList(MediaType.TEXT_HTML);
		}
		return Collections.emptyList();
	}
	
	private boolean isAcceptableURI(HttpServletRequest request){
		boolean result = false;
		if (request != null ){
			if (request.getRequestURI().contains(API_BASE_URL + SLASH)){
				result = true;
			}
		}
		return result;
	}
	
}
