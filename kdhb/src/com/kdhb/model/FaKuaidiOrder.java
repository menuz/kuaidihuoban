package com.kdhb.model;

public class FaKuaidiOrder {
	int id;
	int user_id;
	String order_id;
	String receiver_name;
	String receiver_address;
	String book_time;
	String book_address;

	public FaKuaidiOrder(int id, int user_id, String order_id,
			String receiver_name, String receiver_address, String book_time,
			String book_address) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.order_id = order_id;
		this.receiver_name = receiver_name;
		this.receiver_address = receiver_address;
		this.book_time = book_time;
		this.book_address = book_address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getBook_time() {
		return book_time;
	}

	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}

	public String getBook_address() {
		return book_address;
	}

	public void setBook_address(String book_address) {
		this.book_address = book_address;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FaKuaidiOrder [id=" + id + ",user_id = " + user_id
				+ ",order_id" + order_id + ",receiver_name" + receiver_name
				+ ",receiver_address " + receiver_address + ",book_time "
				+ book_time + ",book_address" + book_address + "]";
	}
}
