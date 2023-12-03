import "./Sidebar.css";

import HomeIcon from "@mui/icons-material/Home";
import LoginIcon from '@mui/icons-material/Login';
import MailOutlineIcon from "@mui/icons-material/MailOutline";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import PermIdentityIcon from "@mui/icons-material/PermIdentity";
import SearchIcon from "@mui/icons-material/Search";
import TwitterIcon from "@mui/icons-material/Twitter";
import { Button } from "@mui/material";
import React from "react";

import SidebarOption from "./SidebarOption";

function Sidebar() {
  return (
    <div className="sidebar">
      <TwitterIcon className="sidebar-twitter-icon" />

      <SidebarOption text="Home" Icon={HomeIcon} href="/"/>
      <SidebarOption text="Explore" Icon={SearchIcon} href="/explore"/>
      <SidebarOption text="Notifications" Icon={NotificationsNoneIcon} href="/notifications"/>
      <SidebarOption text="Messages" Icon={MailOutlineIcon} href="/messages"/>
      <SidebarOption text="Profile" Icon={PermIdentityIcon} href="/home"/>
      <SidebarOption text="Login" Icon={LoginIcon} href="/login"/>

      <Button variant="outlined" className="sidebar-tweet" fullWidth>
        Tweet
      </Button>
    </div>
  );
}

export default Sidebar;
