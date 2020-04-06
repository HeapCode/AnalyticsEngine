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

/**
 * @author Manjunath Sampath
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "url" })
public class Repo implements Serializable {

	@JsonProperty("id")
	private long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("url")
	private String url;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -3014387196275087152L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Repo() {
	}

	/**
	 *
	 * @param name
	 * @param id
	 * @param url
	 */
	public Repo(long id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	@JsonProperty("id")
	public long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	public Repo withId(long id) {
		this.id = id;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Repo withName(String name) {
		this.name = name;
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

	public Repo withUrl(String url) {
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

	public Repo withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("url", url)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(id).append(additionalProperties).append(url).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Repo) == false) {
			return false;
		}
		Repo rhs = ((Repo) other);
		return new EqualsBuilder().append(name, rhs.name).append(id, rhs.id)
				.append(additionalProperties, rhs.additionalProperties).append(url, rhs.url).isEquals();
	}

}