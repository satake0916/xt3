import "./Post.css";

import {
  ChatBubbleOutline,
  FavoriteBorder,
  PublishOutlined,
  Repeat,
} from "@mui/icons-material";
import { Avatar } from "@mui/material";
import React from "react";
import { Link } from "react-router-dom";

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
            <Link to={`/${accountName}`} className="post-header-names">
            <h3>
              {displayName}
              <span className="post-header-special">@{accountName}</span>
            </h3>
            </Link>
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
