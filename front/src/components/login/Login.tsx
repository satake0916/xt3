import "./Login.css"

import React from 'react'

import LoginBox from "./LoginBox";

function Login() {
  return (
    <div className="login">
      <div className="login-header">
        <h2>Login to XT3</h2>
      </div>

      <LoginBox />
    </div>
  )
}

export default Login