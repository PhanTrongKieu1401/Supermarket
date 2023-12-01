package com.example.Supermarket.model;

public class Customer extends Member{
	private MemberCard memberCard;

	public Customer() {
		super();
	}

	public Customer(int id, String username, String password, String name, String phoneNumber, String email,String address, String position, MemberCard memberCard) {
		super(id, username, password, name, phoneNumber, email, address, position);
		this.memberCard = memberCard;
	}

	public MemberCard getMemberCard() {
		return memberCard;
	}

	public void setMemberCard(MemberCard memberCard) {
		this.memberCard = memberCard;
	}

	@Override
	public String toString() {
		return "Customer [memberCard=" + memberCard + "]";
	}	
	
	
}
