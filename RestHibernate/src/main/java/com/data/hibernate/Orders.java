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



@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "oids")
	@SequenceGenerator(name = "oids", sequenceName="oids" ,allocationSize = 1)

	@Column(name="order_id")
	private int order_id;

	@Column(name="quantity")
	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name="p_id")
	private int p_id;
	@Column(name="u_id")
	private int u_id;
	@Column(name="order_price")
	private int order_price;

	@Column(name="sell_id")
	private int sell_id;

	public int getSell_id() {
		return sell_id;
	}
	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}


}
