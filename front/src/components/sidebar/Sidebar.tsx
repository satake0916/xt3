import React from "react";
import SidebarOption from "./SidebarOption";
import "./Sidebar.css";

import TwitterIcon from "@mui/icons-material/Twitter";
import HomeIcon from "@mui/icons-material/Home";
import SearchIcon from "@mui/icons-material/Search";
import MailOutlineIcon from "@mui/icons-material/MailOutline";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import PermIdentityIcon from "@mui/icons-material/PermIdentity";
import { Button } from "@mui/material";

type Props = {};

function Sidebar({}: Props) {
  return (
    <div className="sidebar">
      {/* Icon */}
      <TwitterIcon className="sidebar-twitter-icon" />

      {/* SidebarOptions */}
      <SidebarOption text="Home" Icon={HomeIcon} />
      <SidebarOption text="Search" Icon={SearchIcon} />
      <SidebarOption text="Notification" Icon={NotificationsNoneIcon} />
      <SidebarOption text="Message" Icon={MailOutlineIcon} />
      <SidebarOption text="Profile" Icon={PermIdentityIcon} />

      {/* Tweet Button */}
      <Button variant="outlined" className="sidebar-tweet" fullWidth>
        Tweet
      </Button>
    </div>
  );
}

export default Sidebar;
