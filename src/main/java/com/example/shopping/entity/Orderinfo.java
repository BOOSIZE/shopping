package com.example.shopping.entity;


public class Orderinfo {

  private Long oid;
  private Long sid;
  private String oname;
  private String ocount;
  private String omoney;
  private String otime;
  private String uaccount;
  private String ostatus;
  private String oevaluation;
  private String oevtime;
  private String msg;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getOstatus() {
    return ostatus;
  }

  public void setOstatus(String ostatus) {
    this.ostatus = ostatus;
  }

  public String getOevaluation() {
    return oevaluation;
  }

  public void setOevaluation(String oevaluation) {
    this.oevaluation = oevaluation;
  }

  public String getOevtime() {
    return oevtime;
  }

  public void setOevtime(String oevtime) {
    this.oevtime = oevtime;
  }

  public Long getOid() {
    return oid;
  }

  public void setOid(Long oid) {
    this.oid = oid;
  }


  public Long getSid() {
    return sid;
  }

  public void setSid(Long sid) {
    this.sid = sid;
  }


  public String getOname() {
    return oname;
  }

  public void setOname(String oname) {
    this.oname = oname;
  }


  public String getOcount() {
    return ocount;
  }

  public void setOcount(String ocount) {
    this.ocount = ocount;
  }


  public String getOmoney() {
    return omoney;
  }

  public void setOmoney(String omoney) {
    this.omoney = omoney;
  }


  public String getOtime() {
    return otime;
  }

  public void setOtime(String otime) {
    this.otime = otime;
  }


  public String getUaccount() {
    return uaccount;
  }

  public void setUaccount(String uaccount) {
    this.uaccount = uaccount;
  }

}
