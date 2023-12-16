import "./TweetBox.css";

import { Avatar, Button } from "@mui/material";
import React, { useContext, useState } from "react";

import { TweetsApi } from "../../openapi/generated/apis";
import UserContext from '../../context/UserContext';
import apiConfig from "../../config/ApiConfig";

function TweetBox() {
  const [tweetMessage, setTweetMessage] = useState("");
  const {activeAccountId} = useContext(UserContext)


  async function sendTweet(e: React.MouseEvent<HTMLButtonElement>) {
    e.preventDefault();
    await new TweetsApi(apiConfig).v1TweetsPost({
      tweetReq: {
        tweetText: tweetMessage,
        accountId: activeAccountId,
      },
    }).catch((e) => {
      console.log(e);
    })
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
