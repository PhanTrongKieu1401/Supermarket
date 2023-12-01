package com.example.Supermarket.model;

public class MemberCard {
	private int id;
	private String memberCode;
	public MemberCard() {
		super();
	}
	public MemberCard(int id, String memberCode) {
		super();
		this.id = id;
		this.memberCode = memberCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	@Override
	public String toString() {
		return "MemberCard [id=" + id + ", memberCode=" + memberCode + "]";
	}
	
	
}
