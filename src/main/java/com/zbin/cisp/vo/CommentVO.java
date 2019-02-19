package com.zbin.cisp.vo;

import com.zbin.cisp.domain.Comment;

/**
 * Created by Zbin on 2019-02-19
 */

public class CommentVO extends Comment {

  private String nickname;
  private String avatar;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
