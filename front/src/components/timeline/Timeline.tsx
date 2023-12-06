import "./Timeline.css";

import React, { useContext, useEffect, useState } from "react";

import apiConfig from "../../config/ApiConfig";
import { TweetsApi } from "../../openapi/generated/apis";
import { TweetRes } from "../../openapi/generated/models";
import Post from "./Post";
import TweetBox from "./TweetBox";
import { UserContext } from "../../providers/UserProvider";

function Timeline() {
  const [tweets, setTweets] = useState<TweetRes[]>([]);
  const {activeAccountId} = useContext(UserContext)

  useEffect(() => {
    (async () => {
      const res = await new TweetsApi(apiConfig).tweetsByFolloweeAccountIdGet({accountId: activeAccountId});
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
