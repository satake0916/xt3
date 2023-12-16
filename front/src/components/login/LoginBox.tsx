import Button from '@mui/material/Button';
import React, { useContext, useState } from 'react'

import apiConfig from '../../config/ApiConfig';
import { LoginApi } from '../../openapi/generated/apis';
import UserContext from '../../context/UserContext';
import { useNavigate } from 'react-router-dom';

function LoginBox() {
  const [inputEmail, setEmail] = useState("");
  const [inputPass, setPass] = useState("");
  const [errorMsg, setErrorMsg] = useState<string | null>(null);
  const { setUserId } = useContext(UserContext);
  const navigate = useNavigate()

  async function sendLoginReq(e: React.MouseEvent<HTMLButtonElement>) {
    e.preventDefault();
    // ログイン情報を削除する
    localStorage.clear();
    
    try{
      await new LoginApi(apiConfig).v1LoginPost({
        email: inputEmail,
        pass: inputPass
      }).then((res) => {
        setUserId(res.userId)
      })
      navigate("/")
    } catch (error) {
      setErrorMsg("fail");
    }

  }

  return (
    <div className="login-box">
      <form>
        <div className="tweet-box-input">
          <input
            placeholder="email@example.com"
            type="text"
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            placeholder="pass"
            type="text"
            onChange={(e) => setPass(e.target.value)}
          />
        </div>
        <Button
          className="login-box-login-button"
          fullWidth
          type="submit"
          onClick={(e) => sendLoginReq(e)}
        >
          Login
        </Button>
      </form>

      {
        errorMsg && (
          <div>
            <p>{errorMsg}</p>
          </div>
        )
      }
    </div>
  );
}

export default LoginBox