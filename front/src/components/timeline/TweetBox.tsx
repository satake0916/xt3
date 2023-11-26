import "./TweetBox.css";

import { Avatar, Button } from "@mui/material";
import React from "react";

function TweetBox() {
  return (
    <div className="tweet-box">
      <form>
        <div className="tweet-box-input">
          <Avatar />
          <input placeholder="What is happening?" type="text" />
        </div>
        <Button className="tweet-box-tweet-button" type="submit">
          Tweet
        </Button>
      </form>
    </div>
  );
}

export default TweetBox;
