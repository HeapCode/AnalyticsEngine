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
@JsonPropertyOrder({ "id", "login", "display_login", "gravatar_id", "url", "avatar_url" })
public class Actor implements Serializable {

	@JsonProperty("id")
	private long id;
	@JsonProperty("login")
	private String login;
	@JsonProperty("display_login")
	private String displayLogin;
	@JsonProperty("gravatar_id")
	private String gravatarId;
	@JsonProperty("url")
	private String url;
	@JsonProperty("avatar_url")
	private String avatarUrl;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 1668287808831875384L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Actor() {
	}

	/**
	 *
	 * @param gravatarId
	 * @param avatarUrl
	 * @param id
	 * @param login
	 * @param displayLogin
	 * @param url
	 */
	public Actor(long id, String login, String displayLogin, String gravatarId, String url, String avatarUrl) {
		super();
		this.id = id;
		this.login = login;
		this.displayLogin = displayLogin;
		this.gravatarId = gravatarId;
		this.url = url;
		this.avatarUrl = avatarUrl;
	}

	@JsonProperty("id")
	public long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(long id) {
		this.id = id;
	}

	public Actor withId(long id) {
		this.id = id;
		return this;
	}

	@JsonProperty("login")
	public String getLogin() {
		return login;
	}

	@JsonProperty("login")
	public void setLogin(String login) {
		this.login = login;
	}

	public Actor withLogin(String login) {
		this.login = login;
		return this;
	}

	@JsonProperty("display_login")
	public String getDisplayLogin() {
		return displayLogin;
	}

	@JsonProperty("display_login")
	public void setDisplayLogin(String displayLogin) {
		this.displayLogin = displayLogin;
	}

	public Actor withDisplayLogin(String displayLogin) {
		this.displayLogin = displayLogin;
		return this;
	}

	@JsonProperty("gravatar_id")
	public String getGravatarId() {
		return gravatarId;
	}

	@JsonProperty("gravatar_id")
	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
	}

	public Actor withGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
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

	public Actor withUrl(String url) {
		this.url = url;
		return this;
	}

	@JsonProperty("avatar_url")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	@JsonProperty("avatar_url")
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Actor withAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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

	public Actor withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("login", login).append("displayLogin", displayLogin)
				.append("gravatarId", gravatarId).append("url", url).append("avatarUrl", avatarUrl)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gravatarId).append(avatarUrl).append(id).append(additionalProperties)
				.append(login).append(displayLogin).append(url).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Actor) == false) {
			return false;
		}
		Actor rhs = ((Actor) other);
		return new EqualsBuilder().append(gravatarId, rhs.gravatarId).append(avatarUrl, rhs.avatarUrl)
				.append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(login, rhs.login)
				.append(displayLogin, rhs.displayLogin).append(url, rhs.url).isEquals();
	}

}