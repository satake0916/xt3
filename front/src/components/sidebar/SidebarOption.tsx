import "./SidebarOption.css";

import { SvgIconTypeMap } from "@mui/material";
import { OverridableComponent } from "@mui/material/OverridableComponent";
import React from "react";

type SidebarOptionProps = {
  text: string;
  Icon: OverridableComponent<SvgIconTypeMap<object, "svg">> & { muiName: string };
};

function SidebarOption({ text, Icon }: SidebarOptionProps) {
  return (
    <div className="sidebar-option">
      <Icon />
      <h2>{text}</h2>
    </div>
  );
}

export default SidebarOption;
