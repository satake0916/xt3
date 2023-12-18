import "./Timeline.css";

import React, { useContext, useEffect, useRef, useState } from "react";

import apiConfig from "../../config/ApiConfig";
import { StatusesApi, TweetsApi } from "../../openapi/generated/apis";
import { TweetRes } from "../../openapi/generated/models";
import Post from "../common/Post";
import TweetBox from "./TweetBox";
import UserContext from '../../context/UserContext';
import CircularProgress from "@mui/material/CircularProgress";

function Timeline() {
  const [tweets, setTweets] = useState<TweetRes[]>([]);
  const {activeAccountId} = useContext(UserContext)

  // REVIEW: 
  // リロード時にこのuseEffectがUserProviderのuseEffectよりも先に呼ばれてしまうため、elseに行ってしまう
  // Profileも同様の動きをする
  /*
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
  */

  const loadingRef = useRef(null);
  let oldestTweetId : string | undefined = undefined

  useEffect(() => {
    const observer = new IntersectionObserver(async ([entry]) => {
      if (entry.isIntersecting) {
        const res = activeAccountId ?
          await new StatusesApi(apiConfig).v1StatusesAccountTimelineAccountIdGet(
            {
              accountId: activeAccountId,
              maxId: oldestTweetId,
            }
          )
          : await new TweetsApi(apiConfig).v1TweetsGet(
            {
              maxId: oldestTweetId,
            }
          )
        
        const oldTweets = res.tweets
        oldestTweetId = oldTweets[oldTweets.length - 1].tweetId
        setTweets(displayedTweets => [...displayedTweets, ...oldTweets]);
      }
    });

    if (loadingRef.current) {
      observer.observe(loadingRef.current);
    }

    return () => observer.disconnect();
  }, []);

  return (
    <div className="timeline">
      <div className="timeline-header">
        <h2>Home</h2>
      </div>

      <TweetBox />

      {tweets.map((tweet) => (
        <Post
          key={tweet.tweetId}
          displayName={tweet.displayName}
          accountName={tweet.accountName}
          tweetText={tweet.tweetText}
        />
      ))}

      <div ref={loadingRef} className="timeline-loader">
        <CircularProgress />
      </div>
    </div>
  );
}

export default Timeline;
