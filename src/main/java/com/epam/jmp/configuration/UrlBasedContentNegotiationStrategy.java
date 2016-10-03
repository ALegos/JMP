package com.epam.jmp.configuration;

import static com.epam.jmp.constants.ControllerConstants.API_BASE_URL;
import static com.epam.jmp.constants.ControllerConstants.SLASH;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

import com.epam.jmp.constants.ControllerConstants;

public class UrlBasedContentNegotiationStrategy implements ContentNegotiationStrategy {
	
	private final List<MediaType> contentTypeForAcceptableURLs;
	private String pathParameterName;
	
	/**
	 * This strategy is resolving request content type, based on request URL. If
	 * request URL contain {@link ControllerConstants.API_BASE_URL} then will be
	 * returned {@link contentTypeForAcceptableURLs} , otherwise -
	 * {@link MediaType.TEXT_HTML}
	 *
	 * @param contentTypeForAcceptableURLs
	 *            content type for specific requedsts.
	 */
	
	public UrlBasedContentNegotiationStrategy(MediaType contentTypeForAcceptableURLs) {
		this.contentTypeForAcceptableURLs = Collections.singletonList(contentTypeForAcceptableURLs);
	}
	
	@Override
	public List<MediaType> resolveMediaTypes(NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException {
		List<MediaType> resultList = this.contentTypeForAcceptableURLs;
		HttpServletRequest request = webRequest.getNativeRequest(javax.servlet.http.HttpServletRequest.class);
		if (!isAcceptableURI(request)) {
			resultList = Collections.singletonList(MediaType.TEXT_HTML);
		} else if (StringUtils.isNotBlank(pathParameterName)) {
			if (request.getAttribute(pathParameterName) != null) {
				resultList = Collections.emptyList();
			}
		}
		return resultList;
	}
	
	private boolean isAcceptableURI(HttpServletRequest request) {
		boolean result = false;
		if (request != null) {
			if (request.getRequestURI().contains(API_BASE_URL + SLASH)) {
				result = true;
			}
		}
		return result;
	}
	
	public void setPathParameterName(String pathParameterName) {
		this.pathParameterName = pathParameterName;
		
	}
}
