import "./Post.css";

import {
  ChatBubbleOutline,
  FavoriteBorder,
  PublishOutlined,
  Repeat,
} from "@mui/icons-material";
import { Avatar } from "@mui/material";
import React from "react";

type PostProps = {
  displayName: string;
  accountName: string;
  tweetText: string;
};

function Post({ displayName, accountName, tweetText }: PostProps) {
  return (
    <div className="post">
      <div className="post-avatar">
        <Avatar />
      </div>
      <div className="post-body">
        <div className="post-header">
          <div className="post-header-text">
            <h3>
              {displayName}
              <span className="post-header-special">@{accountName}</span>
            </h3>
          </div>
          <div className="post-header-description">
            <p>{tweetText}</p>
          </div>
        </div>
        <div className="post-footer">
          <ChatBubbleOutline fontSize="small" />
          <Repeat fontSize="small" />
          <FavoriteBorder fontSize="small" />
          <PublishOutlined fontSize="small" />
        </div>
      </div>
    </div>
  );
}

export default Post;
