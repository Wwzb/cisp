package com.zbin.cisp.vo;

import com.zbin.cisp.domain.Article;

/**
 * Created by Zbin on 2019-02-17
 */

public class ArticleVO extends Article {

  private String nickname;
  private String category;
  private String signature;
  private Integer commentCount;
  private String avatar;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
