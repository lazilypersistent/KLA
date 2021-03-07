package com.klalibrary.bean;

public class Attachment {
	private int requestId;
	private String attachmentName;
	private String attachmentType;
	private byte[] attachmentBytes;
	
	public Attachment() {
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public byte[] getAttachmentBytes() {
		return attachmentBytes;
	}

	public void setAttachmentBytes(byte[] attachmentBytes) {
		this.attachmentBytes = attachmentBytes;
	}
	
}
