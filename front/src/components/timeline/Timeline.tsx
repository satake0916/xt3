import "./Timeline.css";

import React, { useContext, useEffect, useState } from "react";

import apiConfig from "../../config/ApiConfig";
import { AccountsApi, StatusesApi, TweetsApi } from "../../openapi/generated/apis";
import { TweetRes } from "../../openapi/generated/models";
import Post from "./Post";
import TweetBox from "./TweetBox";
import { UserContext } from "../../providers/UserProvider";

function Timeline() {
  const [tweets, setTweets] = useState<TweetRes[]>([]);
  const {activeAccountId} = useContext(UserContext)

  useEffect(() => {
    (async () => {
      if(activeAccountId){
        const res = await new StatusesApi(apiConfig).v1StatusesAccountTimelineAccountIdGet(
          {
            accountId: activeAccountId
          }
        )
        setTweets(res.tweets);
      }else{
        const res = await new TweetsApi(apiConfig).v1TweetsGet()
        setTweets(res.tweets)
      }
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
          displayName={tweet.displayName}
          accountName={tweet.accountName}
          tweetText={tweet.tweetText}
        />
      ))}
    </div>
  );
}

export default Timeline;
