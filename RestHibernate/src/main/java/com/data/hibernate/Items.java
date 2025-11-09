package com.data.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity class representing an item in the bidding application.
 * Maps to the 'items' table in the database.
 */
@NamedQueries({
	@NamedQuery(name = Itemqueries.FETCH_All_ITEMS, query = Itemqueries.FETCH_All_ITEMS_QUERY),
	@NamedQuery(name = Itemqueries.FETCH_All_ITEMS_ON_SEARCH, query = Itemqueries.FETCH_All_ITEMS_ON_SEARCH_QUERY)
})
@Entity
@Table(name = "items")
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pidseq")
	@SequenceGenerator(name = "pidseq", sequenceName = "pidseq", allocationSize = 1)
	@Column(name = "p_id")
	private int p_id;

	@Column(name = "p_name")
	private String p_name;

	@Column(name = "p_desc")
	private String p_desc;

	@Column(name = "u_id")
	private int id;

	// Getters and Setters

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
