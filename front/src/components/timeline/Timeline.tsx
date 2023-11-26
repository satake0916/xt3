import "./Timeline.css";

import React from "react";

import Post from "./Post";
import TweetBox from "./TweetBox";

function Timeline() {
  return (
    <div className="timeline">
      <div className="timeline-header">
        <h2>Home</h2>
      </div>

      <TweetBox />

      <Post />
    </div>
  );
}

export default Timeline;
