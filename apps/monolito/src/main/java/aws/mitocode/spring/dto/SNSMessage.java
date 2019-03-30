package aws.mitocode.spring.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SNSMessage implements Serializable{

	private static final long serialVersionUID = -5717348123154885515L;

	@JsonProperty("Type")
	private String type;
	@JsonProperty("MessageId")
	private String messageId;
	@JsonProperty("TopicArn")
	private String topicArn;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("Timestamp")
	private String timestamp;
	@JsonProperty("SignatureVersion")
	private String signatureVersion;
	@JsonProperty("Signature")
	private String signature;
	@JsonProperty("SigningCertURL")
	private String signingCertURL;
	@JsonProperty("UnsubscribeURL")
	private String unsubscribeURL;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getTopicARN() {
		return topicArn;
	}
	public void setTopicARN(String topicARN) {
		this.topicArn = topicARN;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignatureVersion() {
		return signatureVersion;
	}
	public void setSignatureVersion(String signatureVersion) {
		this.signatureVersion = signatureVersion;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSigningCertURL() {
		return signingCertURL;
	}
	public void setSigningCertURL(String signingCertURL) {
		this.signingCertURL = signingCertURL;
	}
	public String getUnsubscribeURL() {
		return unsubscribeURL;
	}
	public void setUnsubscribeURL(String unsubscribeURL) {
		this.unsubscribeURL = unsubscribeURL;
	}
}
