package com.codecool.model.locations;

public class ContactInfo {
  private String address;
  private String phone;
  private String email;
  private String website;
  private String facebook;
  private String instagram;

  public ContactInfo(String address, String phone, String email) {
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  public void addWebsite(String website) {
    this.website = website;
  }

  public void addFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void addInstagram(String instagram) {
    this.instagram = instagram;
  }

}
