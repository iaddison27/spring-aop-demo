package org.test.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.test.util.LocalDateTimeSerializer;

@Entity
@Table(name = "AUDIT")
@NamedQueries({
    @NamedQuery(name = "Audit.findAll", 
    	query = "SELECT a FROM Audit a order by a.id"
    )
})
public class Audit {

	@Id
	@GeneratedValue
	@Column(name = "AUDIT_ID")
	private long id;

	@Column(name = "AUDIT_CLASS")
	private String className;
	
	@Column(name = "AUDIT_METHOD")
	private String methodName;

	@Column(name = "AUDIT_ARGS")
	private String args;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)  
	@Column(name = "AUDIT_TIMESTAMP_UTC")
	// Don't need @Temporal on Java 8 Date/Time classes
	private LocalDateTime timestamp;
	
	public Audit() {
		super();
	}

	public Audit(String className, String methodName, String args, LocalDateTime timestamp) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.args = args;
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
