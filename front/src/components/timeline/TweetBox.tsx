import "./TweetBox.css";

import { Avatar, Button } from "@mui/material";
import React, { useState } from "react";

import { TweetsApi } from "../../openapi/generated/apis";

function TweetBox() {
  const [tweetMessage, setTweetMessage] = useState("");

  async function sendTweet(e: React.MouseEvent<HTMLButtonElement>) {
    e.preventDefault();
    await new TweetsApi().tweetsPost({
      tweetReq: {
        tweetText: tweetMessage,
        accountId: 1,
      },
    });
    setTweetMessage("")
  }

  return (
    <div className="tweet-box">
      <form>
        <div className="tweet-box-input">
          <Avatar />
          <input
            value={tweetMessage}
            placeholder="What is happening?"
            type="text"
            onChange={(e) => setTweetMessage(e.target.value)}
          />
        </div>
        <Button
          className="tweet-box-tweet-button"
          type="submit"
          onClick={(e) => sendTweet(e)}
        >
          Tweet
        </Button>
      </form>
    </div>
  );
}

export default TweetBox;
