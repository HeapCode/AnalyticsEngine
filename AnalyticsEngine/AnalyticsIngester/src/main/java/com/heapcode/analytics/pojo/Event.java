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
@JsonPropertyOrder({ "id", "type", "actor", "repo", "payload", "public", "created_at" })
public class Event implements Serializable {

	@JsonProperty("id")
	private String id;
	@JsonProperty("type")
	private String type;
	@JsonProperty("actor")
	private Actor actor;
	@JsonProperty("repo")
	private Repo repo;
	@JsonProperty("payload")
	private Payload payload;
	@JsonProperty("public")
	private boolean _public;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 61606160821752087L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Event() {
	}

	/**
	 *
	 * @param actor
	 * @param createdAt
	 * @param payload
	 * @param _public
	 * @param repo
	 * @param id
	 * @param type
	 */
	public Event(String id, String type, Actor actor, Repo repo, Payload payload, boolean _public, String createdAt) {
		super();
		this.id = id;
		this.type = type;
		this.actor = actor;
		this.repo = repo;
		this.payload = payload;
		this._public = _public;
		this.createdAt = createdAt;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public Event withId(String id) {
		this.id = id;
		return this;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public Event withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("actor")
	public Actor getActor() {
		return actor;
	}

	@JsonProperty("actor")
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Event withActor(Actor actor) {
		this.actor = actor;
		return this;
	}

	@JsonProperty("repo")
	public Repo getRepo() {
		return repo;
	}

	@JsonProperty("repo")
	public void setRepo(Repo repo) {
		this.repo = repo;
	}

	public Event withRepo(Repo repo) {
		this.repo = repo;
		return this;
	}

	@JsonProperty("payload")
	public Payload getPayload() {
		return payload;
	}

	@JsonProperty("payload")
	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public Event withPayload(Payload payload) {
		this.payload = payload;
		return this;
	}

	@JsonProperty("public")
	public boolean isPublic() {
		return _public;
	}

	@JsonProperty("public")
	public void setPublic(boolean _public) {
		this._public = _public;
	}

	public Event withPublic(boolean _public) {
		this._public = _public;
		return this;
	}

	@JsonProperty("created_at")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Event withCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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

	public Event withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("type", type).append("actor", actor)
				.append("repo", repo).append("payload", payload).append("_public", _public)
				.append("createdAt", createdAt).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(actor).append(createdAt).append(payload).append(_public).append(repo)
				.append(id).append(additionalProperties).append(type).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Event) == false) {
			return false;
		}
		Event rhs = ((Event) other);
		return new EqualsBuilder().append(actor, rhs.actor).append(createdAt, rhs.createdAt)
				.append(payload, rhs.payload).append(_public, rhs._public).append(repo, rhs.repo).append(id, rhs.id)
				.append(additionalProperties, rhs.additionalProperties).append(type, rhs.type).isEquals();
	}
}