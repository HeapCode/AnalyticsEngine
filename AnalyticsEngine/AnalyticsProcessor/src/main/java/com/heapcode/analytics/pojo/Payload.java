package com.heapcode.analytics.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "push_id", "size", "distinct_size", "ref", "head", "before", "commits", "ref_type",
		"master_branch", "description", "pusher_type" })
public class Payload implements Serializable {

	@JsonProperty("push_id")
	private long pushId;
	@JsonProperty("size")
	private long size;
	@JsonProperty("distinct_size")
	private long distinctSize;
	@JsonProperty("ref")
	private Object ref;
	@JsonProperty("head")
	private String head;
	@JsonProperty("before")
	private String before;
	@JsonProperty("commits")
	private List<Commit> commits = null;
	@JsonProperty("ref_type")
	private String refType;
	@JsonProperty("master_branch")
	private String masterBranch;
	@JsonProperty("description")
	private String description;
	@JsonProperty("pusher_type")
	private String pusherType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -7606042475857888452L;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Payload() {
	}

	/**
	 *
	 * @param pushId
	 * @param head
	 * @param ref
	 * @param size
	 * @param before
	 * @param refType
	 * @param masterBranch
	 * @param distinctSize
	 * @param commits
	 * @param description
	 * @param pusherType
	 */
	public Payload(long pushId, long size, long distinctSize, Object ref, String head, String before,
			List<Commit> commits, String refType, String masterBranch, String description, String pusherType) {
		super();
		this.pushId = pushId;
		this.size = size;
		this.distinctSize = distinctSize;
		this.ref = ref;
		this.head = head;
		this.before = before;
		this.commits = commits;
		this.refType = refType;
		this.masterBranch = masterBranch;
		this.description = description;
		this.pusherType = pusherType;
	}

	@JsonProperty("push_id")
	public long getPushId() {
		return pushId;
	}

	@JsonProperty("push_id")
	public void setPushId(long pushId) {
		this.pushId = pushId;
	}

	public Payload withPushId(long pushId) {
		this.pushId = pushId;
		return this;
	}

	@JsonProperty("size")
	public long getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(long size) {
		this.size = size;
	}

	public Payload withSize(long size) {
		this.size = size;
		return this;
	}

	@JsonProperty("distinct_size")
	public long getDistinctSize() {
		return distinctSize;
	}

	@JsonProperty("distinct_size")
	public void setDistinctSize(long distinctSize) {
		this.distinctSize = distinctSize;
	}

	public Payload withDistinctSize(long distinctSize) {
		this.distinctSize = distinctSize;
		return this;
	}

	@JsonProperty("ref")
	public Object getRef() {
		return ref;
	}

	@JsonProperty("ref")
	public void setRef(Object ref) {
		this.ref = ref;
	}

	public Payload withRef(Object ref) {
		this.ref = ref;
		return this;
	}

	@JsonProperty("head")
	public String getHead() {
		return head;
	}

	@JsonProperty("head")
	public void setHead(String head) {
		this.head = head;
	}

	public Payload withHead(String head) {
		this.head = head;
		return this;
	}

	@JsonProperty("before")
	public String getBefore() {
		return before;
	}

	@JsonProperty("before")
	public void setBefore(String before) {
		this.before = before;
	}

	public Payload withBefore(String before) {
		this.before = before;
		return this;
	}

	@JsonProperty("commits")
	public List<Commit> getCommits() {
		return commits;
	}

	@JsonProperty("commits")
	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}

	public Payload withCommits(List<Commit> commits) {
		this.commits = commits;
		return this;
	}

	@JsonProperty("ref_type")
	public String getRefType() {
		return refType;
	}

	@JsonProperty("ref_type")
	public void setRefType(String refType) {
		this.refType = refType;
	}

	public Payload withRefType(String refType) {
		this.refType = refType;
		return this;
	}

	@JsonProperty("master_branch")
	public String getMasterBranch() {
		return masterBranch;
	}

	@JsonProperty("master_branch")
	public void setMasterBranch(String masterBranch) {
		this.masterBranch = masterBranch;
	}

	public Payload withMasterBranch(String masterBranch) {
		this.masterBranch = masterBranch;
		return this;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Payload withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("pusher_type")
	public String getPusherType() {
		return pusherType;
	}

	@JsonProperty("pusher_type")
	public void setPusherType(String pusherType) {
		this.pusherType = pusherType;
	}

	public Payload withPusherType(String pusherType) {
		this.pusherType = pusherType;
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

	public Payload withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("pushId", pushId).append("size", size)
				.append("distinctSize", distinctSize).append("ref", ref).append("head", head).append("before", before)
				.append("commits", commits).append("refType", refType).append("masterBranch", masterBranch)
				.append("description", description).append("pusherType", pusherType)
				.append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(before).append(refType).append(distinctSize).append(description)
				.append(pushId).append(head).append(ref).append(size).append(masterBranch).append(commits)
				.append(additionalProperties).append(pusherType).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Payload) == false) {
			return false;
		}
		Payload rhs = ((Payload) other);
		return new EqualsBuilder().append(before, rhs.before).append(refType, rhs.refType)
				.append(distinctSize, rhs.distinctSize).append(description, rhs.description).append(pushId, rhs.pushId)
				.append(head, rhs.head).append(ref, rhs.ref).append(size, rhs.size)
				.append(masterBranch, rhs.masterBranch).append(commits, rhs.commits)
				.append(additionalProperties, rhs.additionalProperties).append(pusherType, rhs.pusherType).isEquals();
	}
}