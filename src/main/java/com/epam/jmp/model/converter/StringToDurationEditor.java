package com.epam.jmp.model.converter;

import java.beans.PropertyEditorSupport;
import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import com.epam.jmp.constants.UtilConstants;

public class StringToDurationEditor extends PropertyEditorSupport {
	
	@Override
	public String getAsText() {
		String result = "";
		if (getValue() != null) {
			Duration duration = (Duration) getValue();
			result = DurationFormatUtils.formatDuration(duration.toMillis(), UtilConstants.DURATION_FORMAT_PATTERN,
					true);
		}
		return result;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Duration dur = null;
		if (StringUtils.isNotBlank(text)) {
			String[] fields = text.split(":");
			dur = Duration.parse(String.format("PT%sH%sM", fields[0], fields[1]));
		}
		super.setValue(dur);
	}
	
}
