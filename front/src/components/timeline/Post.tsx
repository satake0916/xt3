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
  accountName: string;
  accountId: string;
  text: string;
};

function Post({ accountName, accountId, text }: PostProps) {
  return (
    <div className="post">
      <div className="post-avatar">
        <Avatar />
      </div>
      <div className="post-body">
        <div className="post-header">
          <div className="post-header-text">
            <h3>
              {accountName}
              <span className="post-header-special">@{accountId}</span>
            </h3>
          </div>
          <div className="post-header-description">
            <p>{text}</p>
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
