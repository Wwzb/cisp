package com.zbin.cisp.service.impl;

import com.zbin.cisp.dao.CommentMapper;
import com.zbin.cisp.dao.UserMapper;
import com.zbin.cisp.domain.Comment;
import com.zbin.cisp.domain.User;
import com.zbin.cisp.service.CommentService;
import com.zbin.cisp.vo.CommentVO;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by Zbin on 2019-02-19
 */
@Service
public class CommentServiceImpl implements CommentService {

  @Resource
  CommentMapper commentMapper;

  @Resource
  UserMapper userMapper;

  @Override
  public void create(Comment comment) {
    commentMapper.create(comment);
  }

  @Override
  public List<CommentVO> getCommentByArticleId(Integer articleId) {
    List<CommentVO> commentVOList = commentMapper.getCommentByArticleId(articleId);
    for (CommentVO commentVO : commentVOList) {
      User user = userMapper.getUserById(commentVO.getUserId());
      commentVO.setAvatar(user.getAvatar());
      commentVO.setNickname(user.getNickname());
    }
    return commentVOList;
  }
}
