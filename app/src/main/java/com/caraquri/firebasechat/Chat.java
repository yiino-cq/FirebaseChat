package com.caraquri.firebasechat;

public class Chat {

  private String name;
  private String text;
  private String date;

  public Chat() {
  }

  public Chat(String name, String message, String date) {
    this.name = name;
    this.text = message;
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public String getText() {
    return text;
  }

  public String getDate() {
    return date;
  }
}
