package com.kdhb.model;

import java.sql.Timestamp;

public class ShouKuaidiOrder {

	int id;
	int release_user_id;
	String order_id;
	Timestamp ct;
	int pack_sign_timeinterval;
	int pack_company;
	int pack_size;
	int pack_dest;
	int pack_money;
	String pack_to_dest_time;
	String order_valid_time;
	int accept_user_id;
	int status;
	
	public ShouKuaidiOrder() {
		
	}

	@Override
	public String toString() {
		return "ShouKuaidiOrder [id=" + id + ", release_user_id="
				+ release_user_id + ", order_id=" + order_id + ", ct=" + ct
				+ ", pack_sign_timeinterval=" + pack_sign_timeinterval
				+ ", pack_company=" + pack_company + ", pack_size=" + pack_size
				+ ", pack_dest=" + pack_dest + ", pack_money=" + pack_money
				+ ", pack_to_dest_time=" + pack_to_dest_time
				+ ", order_valid_time=" + order_valid_time
				+ ", accept_user_id=" + accept_user_id + ", status=" + status
				+ "]";
	}

	public ShouKuaidiOrder(int id, int release_user_id, String order_id,
			Timestamp ct, int pack_sign_timeinterval, int pack_company,
			int pack_size, int pack_dest, int pack_money,
			String pack_to_dest_time, String order_valid_time,
			int accept_user_id, int status) {
		super();
		this.id = id;
		this.release_user_id = release_user_id;
		this.order_id = order_id;
		this.ct = ct;
		this.pack_sign_timeinterval = pack_sign_timeinterval;
		this.pack_company = pack_company;
		this.pack_size = pack_size;
		this.pack_dest = pack_dest;
		this.pack_money = pack_money;
		this.pack_to_dest_time = pack_to_dest_time;
		this.order_valid_time = order_valid_time;
		this.accept_user_id = accept_user_id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRelease_user_id() {
		return release_user_id;
	}

	public void setRelease_user_id(int release_user_id) {
		this.release_user_id = release_user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Timestamp getCt() {
		return ct;
	}

	public void setCt(Timestamp ct) {
		this.ct = ct;
	}

	public int getPack_sign_timeinterval() {
		return pack_sign_timeinterval;
	}

	public void setPack_sign_timeinterval(int pack_sign_timeinterval) {
		this.pack_sign_timeinterval = pack_sign_timeinterval;
	}

	public int getPack_company() {
		return pack_company;
	}

	public void setPack_company(int pack_company) {
		this.pack_company = pack_company;
	}

	public int getPack_size() {
		return pack_size;
	}

	public void setPack_size(int pack_size) {
		this.pack_size = pack_size;
	}

	public int getPack_dest() {
		return pack_dest;
	}

	public void setPack_dest(int pack_dest) {
		this.pack_dest = pack_dest;
	}

	public int getPack_money() {
		return pack_money;
	}

	public void setPack_money(int pack_money) {
		this.pack_money = pack_money;
	}

	public String getPack_to_dest_time() {
		return pack_to_dest_time;
	}

	public void setPack_to_dest_time(String pack_to_dest_time) {
		this.pack_to_dest_time = pack_to_dest_time;
	}

	public String getOrder_valid_time() {
		return order_valid_time;
	}

	public void setOrder_valid_time(String order_valid_time) {
		this.order_valid_time = order_valid_time;
	}

	public int getAccept_user_id() {
		return accept_user_id;
	}

	public void setAccept_user_id(int accept_user_id) {
		this.accept_user_id = accept_user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}


