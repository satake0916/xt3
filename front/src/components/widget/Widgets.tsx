import { Search } from "@mui/icons-material";
import { TwitterTimelineEmbed, TwitterShareButton } from "react-twitter-embed";
import React from "react";
import "./Widgets.css";

type Props = {};

function Widgets({}: Props) {
  return (
    <div className="widgets">
      <div className="widgets-input">
        <Search className="widgets-search-icon"></Search>
        <input placeholder="Search" type="text"></input>
      </div>
      <div className="widgets-widgets-container">
        <h2>What's happening</h2>
        <TwitterTimelineEmbed
          sourceType="profile"
          screenName="Nintendo"
          options={{ height: 600 }}
        />
      </div>
    </div>
  );
}

export default Widgets;
