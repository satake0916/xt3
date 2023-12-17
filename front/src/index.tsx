import "./index.css";

import React from "react";
import ReactDOM from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import App from "./App";
import ErrorPage from "./components/errorpage/ErrorPage";
import Login from "./components/login/Login";
import Timeline from "./components/timeline/Timeline";
import UserProvider from "./providers/UserProvider";
import Profile from "./components/profile/Profile";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement,
);

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        element: <Timeline />,
        index: true
      },
      {
        path: "login",
        element: <Login />,
      },
      {
        path: ":accountName",
        element: <Profile />,
      },
    ],
  }
]);

root.render(
  <React.StrictMode>
    <UserProvider>
      <RouterProvider router={router} />
    </UserProvider>
  </React.StrictMode>
);
