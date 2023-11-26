import React from "react";
import "./Timeline.css";
import TweetBox from "./TweetBox";
import Post from "./Post";

type Props = {};

function Timeline({}: Props) {
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
