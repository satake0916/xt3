import "./App.css";

import React from "react";
import { Outlet } from "react-router-dom";

import Sidebar from "./components/sidebar/Sidebar";
import Widgets from "./components/widget/Widgets";


function App() {
  return (
    <div className="app">
      
      <Sidebar />
      
      <Outlet />
  
      <Widgets />
  
    </div>
  );
}

export default App;
