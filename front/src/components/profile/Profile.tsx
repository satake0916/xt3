import "./Profile.css";

import React, { useContext, useEffect, useState } from "react";

import apiConfig from "../../config/ApiConfig";
import { AccountsApi } from "../../openapi/generated/apis";
import { AccountRes, TweetRes } from "../../openapi/generated/models";
import UserContext from '../../context/UserContext';
import Post from "../common/Post";
import ProfileBox from "./ProfileBox";

type props = {
  accountId: string | undefined
}

function Profile({accountId}: props) {
  const [accountInfo, setAccountInfo] = useState<AccountRes>();
  const [ownTweets, setOwnTweets] = useState<TweetRes[]>([]);
  const {activeAccountId} = useContext(UserContext)

  useEffect(() => {
    const targetAccountId = accountId ?? activeAccountId;

    if(targetAccountId){
      (async () => {
          const res = await new AccountsApi(apiConfig).v1AccountsAccountIdGet(
            {
              accountId: targetAccountId
            }
          )
          setAccountInfo(res)
      })();

      (async () => {
        const res = await new AccountsApi(apiConfig).v1AccountsAccountIdTweetsGet(
          {
            accountId: targetAccountId
          }
        )
        setOwnTweets(res.tweets)
      })();
    }
  })

  return (
    <div className="profile">
      <ProfileBox 
        displayName={accountInfo?.displayName}
        accountName={accountInfo?.accountName}
        iconImageUrl={accountInfo?.profileImageUrl}
        description={accountInfo?.profileDescription}
      />

      {ownTweets.map((tweet) => (
        <Post
          displayName={tweet.displayName}
          accountName={tweet.accountName}
          tweetText={tweet.tweetText}
        />
      ))}
    </div>
  );
}

export default Profile;
