package com.data.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = BidsQueries.FIND_BIDS_BY_U_ID, query = BidsQueries.FIND_BIDS_BY_U_ID_QUERY) ,
	@NamedQuery(name = BidsQueries.FIND_BIDS_BY_P_ID, query = BidsQueries.FIND_BIDS_BY_P_ID_QUERY),
	@NamedQuery(name = BidsQueries.FETCH_All_BIDS, query = BidsQueries.FETCH_All_BIDS_QUERY)})
@Entity
@Table(name = "bids")

public class Bids {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "bid_ids")
	@SequenceGenerator(name = "bid_ids", sequenceName = "bid_ids", allocationSize = 1)
	@Column(name="bid_id")
	private Integer bid_id;


	@Column(name="bid_price")
	private Integer bid_price;


	@Column(name ="u_id")
	private Integer id;

	@Column(name="p_id")
	private Integer p_id;


	public Integer getBid_price() {
		return bid_price;
	}

	public void setBid_price(Integer bid_price) {
		this.bid_price = bid_price;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getBid_id() {
		return bid_id;
	}

	public void setBid_id(Integer bid_id) {
		this.bid_id = bid_id;
	}



}
