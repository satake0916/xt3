import "./SidebarOption.css";

import { SvgIconTypeMap } from "@mui/material";
import { OverridableComponent } from "@mui/material/OverridableComponent";
import React from "react";
import { Link } from "react-router-dom";

type SidebarOptionProps = {
  text: string;
  Icon: OverridableComponent<SvgIconTypeMap<object, "svg">> & {
    muiName: string;
  };
  href: string;
};

function SidebarOption({ text, Icon, href }: SidebarOptionProps) {
  return (
    <Link to={href} className="sidebar-option-a">
      <div className="sidebar-option">
        <Icon />
        <h2>{text}</h2>
      </div>
    </Link>
  );
}

export default SidebarOption;
