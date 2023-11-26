import { Avatar, Button } from "@mui/material";
import React from "react";
import "./TweetBox.css";

type Props = {};

function TweetBox({}: Props) {
  return (
    <div className="tweet-box">
      <form>
        <div className="tweet-box-input">
          <Avatar />
          <input placeholder="What is happening?" type="text"></input>
        </div>
        <Button className="tweet-box-tweet-button" type="submit">
          Tweet
        </Button>
      </form>
    </div>
  );
}

export default TweetBox;
