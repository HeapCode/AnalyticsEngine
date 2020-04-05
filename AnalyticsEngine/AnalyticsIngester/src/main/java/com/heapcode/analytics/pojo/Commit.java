package com.heapcode.analytics.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sha", "author", "message", "distinct", "url" })
public class Commit implements Serializable {

	@JsonProperty("sha")
	private String sha;
	@JsonProperty("author")
	private Author author;
	@JsonProperty("message")
	private String message;
	@JsonProperty("distinct")
	private boolean distinct;
	@JsonProperty("url")
	private String url;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -1434207881411043474L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Commit() {
	}

	/**
	 *
	 * @param author
	 * @param distinct
	 * @param message
	 * @param sha
	 * @param url
	 */
	public Commit(String sha, Author author, String message, boolean distinct, String url) {
		super();
		this.sha = sha;
		this.author = author;
		this.message = message;
		this.distinct = distinct;
		this.url = url;
	}

	@JsonProperty("sha")
	public String getSha() {
		return sha;
	}

	@JsonProperty("sha")
	public void setSha(String sha) {
		this.sha = sha;
	}

	public Commit withSha(String sha) {
		this.sha = sha;
		return this;
	}

	@JsonProperty("author")
	public Author getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Commit withAuthor(Author author) {
		this.author = author;
		return this;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	public Commit withMessage(String message) {
		this.message = message;
		return this;
	}

	@JsonProperty("distinct")
	public boolean isDistinct() {
		return distinct;
	}

	@JsonProperty("distinct")
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public Commit withDistinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}

	public Commit withUrl(String url) {
		this.url = url;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Commit withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sha", sha).append("author", author).append("message", message)
				.append("distinct", distinct).append("url", url).append("additionalProperties", additionalProperties)
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(author).append(distinct).append(additionalProperties).append(message)
				.append(sha).append(url).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Commit) == false) {
			return false;
		}
		Commit rhs = ((Commit) other);
		return new EqualsBuilder().append(author, rhs.author).append(distinct, rhs.distinct)
				.append(additionalProperties, rhs.additionalProperties).append(message, rhs.message)
				.append(sha, rhs.sha).append(url, rhs.url).isEquals();
	}

}