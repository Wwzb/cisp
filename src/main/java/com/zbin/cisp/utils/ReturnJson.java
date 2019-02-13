package com.zbin.cisp.utils;

public class ReturnJson {

  protected Integer code;
  protected String msg;
  protected Integer count;
  protected Object data;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public ReturnJson(Integer code, String msg, Integer count, Object data) {
    this.code = code;
    this.msg = msg;
    this.count = count;
    this.data = data;
  }

  @Override
  public String toString() {
    return "ReturnJson{" +
      "code=" + code +
      ", msg='" + msg + '\'' +
      ", count=" + count +
      ", data=" + data +
      '}';
  }
}
