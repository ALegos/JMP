package com.epam.jmp.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

public class UrlBasedContentNegotiationStrategy implements ContentNegotiationStrategy {
	
	@Override
	public List<MediaType> resolveMediaTypes(NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException {
		if (webRequest.getContextPath() != null) {
			throw new HttpMediaTypeNotAcceptableException("For this url");
		}
		return Collections.emptyList();
	}
	
}
