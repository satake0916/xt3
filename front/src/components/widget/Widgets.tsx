import "./Widgets.css";

import { Search } from "@mui/icons-material";
import React from "react";
import { TwitterTimelineEmbed } from "react-twitter-embed";

function Widgets() {
  return (
    <div className="widgets">
      <div className="widgets-input">
        <Search className="widgets-search-icon" />
        <input placeholder="Search" type="text" />
      </div>
      <div className="widgets-widgets-container">
        <h2>What&apos;s happening</h2>
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
