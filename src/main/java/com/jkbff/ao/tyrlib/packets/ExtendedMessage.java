package com.jkbff.ao.tyrlib.packets;

import java.util.List;

public class ExtendedMessage {

	private final long categoryId;
	private final long instanceId;
	private final String messageTemplate;
	private final List<Object> params;

	public ExtendedMessage(long categoryId, long instanceId, String messageTemplate, List<Object> params) {
		this.categoryId = categoryId;
		this.instanceId = instanceId;
		this.messageTemplate = messageTemplate;
		this.params = params;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public long getInstanceId() {
		return instanceId;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public List<Object> getParams() {
		return params;
	}

	public String getFormattedMessage() {
		return String.format(messageTemplate, params.toArray());
	}

	public String toString() {
		return getFormattedMessage();
	}
}
