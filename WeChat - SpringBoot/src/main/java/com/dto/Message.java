package com.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue
	@Column(name = "message_id")
	private int message_id;

	
	@Column(name = "sender_id")
	private int sender_id;

	@Column(name = "recipient_id")
	private int recipient_id;

	@Column(name = "message_text")
	private String message_text;
	
	@Column(name = "attachment_path", columnDefinition = "VARCHAR(255) DEFAULT NULL")
	private String attachmentPath;
	
	@Column(name="file_name", columnDefinition = "VARCHAR(255) DEFAULT NULL")
	private String fileName;

	@Column(name = "timestamp")
	private Timestamp timestamp;
	

	public Message() {
		super();
	}

	public Message(int message_id, int sender_id, int recipient_id, String message_text,String fileName, Timestamp timestamp) {
		super();
		this.message_id = message_id;
		this.sender_id = sender_id;
		this.recipient_id = recipient_id;
		this.message_text = message_text;
		this.fileName = fileName;
		this.timestamp = timestamp;
	}

	public int getMessageId() {
		return message_id;
	}

	public void setMessageId(int message_id) {
		this.message_id = message_id;
	}

	public int getSender() {
		return sender_id;
	}

	public void setSender(int sender_id) {
		this.sender_id = sender_id;
	}

	public int getRecipient() {
		return recipient_id;
	}

	public void setRecipient(int recipient_id) {
		this.recipient_id = recipient_id;
	}

	public String getMessageText() {
		return message_text;
	}

	public void setMessageText(String message_text) {
		this.message_text = message_text;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", sender_id=" + sender_id + ", recipient_id=" + recipient_id
				+ ", message_text=" + message_text + ", attachmentPath=" + attachmentPath + ", fileName=" + fileName
				+ ", timestamp=" + timestamp + "]";
	}

}
