import "./Timeline.css";

import React, { useEffect, useState } from "react";

import { TweetsApi } from "../../openapi/generated/apis";
import { TweetRes } from "../../openapi/generated/models";
import Post from "./Post";
import TweetBox from "./TweetBox";

function Timeline() {
  const [tweets, setTweets] = useState<TweetRes[]>([]);

  useEffect(() => {
    (async () => {
      const res = await new TweetsApi().tweetsGet();
      setTweets(res.tweets);
    })();
  }, []);

  return (
    <div className="timeline">
      <div className="timeline-header">
        <h2>Home</h2>
      </div>

      <TweetBox />

      {tweets.map((tweet) => (
        <Post
          displayName={tweet.accountId.toString()}
          accountName={tweet.accountId.toString()}
          tweetText={tweet.tweetText}
        />
      ))}
    </div>
  );
}

export default Timeline;
