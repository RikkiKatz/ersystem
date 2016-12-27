package com.revature.beans;
/**
 * JavaBean for Reimbursement- contains all reimbursement properties
 * @author Rikki
 *
 */
import java.util.Date;

public class Reimbursement {

	private int id;
	private double amount;
	private Date date_submitted;
	private Date date_resolved;
	private String description;
	private User author_id;
	private User resolver_id;
	private ReimbStatus status_id;
	private ReimbType type_id;
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", date_submitted=" + date_submitted
				+ ", date_resolved=" + date_resolved + ", description=" + description + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
	
	public Reimbursement(int id, double amount, Date date_submitted, Date date_resolved, String description,
			User author_id, User resolver_id, ReimbStatus status_id, ReimbType type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.date_submitted = date_submitted;
		this.date_resolved = date_resolved;
		this.description = description;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}
	
	public Reimbursement() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate_submitted() {
		return date_submitted;
	}
	public void setDate_submitted(Date date_submitted) {
		this.date_submitted = date_submitted;
	}
	public Date getDate_resolved() {
		return date_resolved;
	}
	public void setDate_resolved(Date date_resolved) {
		this.date_resolved = date_resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(User author_id) {
		this.author_id = author_id;
	}
	public User getResolver_id() {
		return resolver_id;
	}
	public void setResolver_id(User resolver_id) {
		this.resolver_id = resolver_id;
	}
	public ReimbStatus getStatus_id() {
		return status_id;
	}
	public void setStatus_id(ReimbStatus status_id) {
		this.status_id = status_id;
	}
	public ReimbType getType_id() {
		return type_id;
	}
	public void setType_id(ReimbType type_id) {
		this.type_id = type_id;
	}
	
	
}
