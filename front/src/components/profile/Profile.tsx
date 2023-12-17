import "./Profile.css";

import React, { useContext, useEffect, useState } from "react";

import apiConfig from "../../config/ApiConfig";
import { AccountsApi } from "../../openapi/generated/apis";
import { AccountRes, TweetRes } from "../../openapi/generated/models";
import UserContext from '../../context/UserContext';
import Post from "../common/Post";
import ProfileBox from "./ProfileBox";
import { useParams } from "react-router-dom";

function Profile() {
  const [accountInfo, setAccountInfo] = useState<AccountRes>();
  const [ownTweets, setOwnTweets] = useState<TweetRes[]>([]);
  // const {activeAccountId} = useContext(UserContext)
  const {accountName} = useParams();

  useEffect(() => {
    // REVIEW: 1page1apiに反している。Accountを取得する際にtweetsも同時に取得したい。

    if(accountName){
      (async () => {
          const res = await new AccountsApi(apiConfig).v1AccountsByAccountNameAccountNameGet(
            {
              accountName: accountName
            }
          )
          setAccountInfo(res)
      })();
    }

    if(accountInfo){
      (async () => {
        const res = await new AccountsApi(apiConfig).v1AccountsAccountIdTweetsGet(
          {
            accountId: accountInfo.accountId
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
