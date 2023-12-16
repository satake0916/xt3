import {
  ReactNode,
  useEffect,
  useState,
} from "react";

import apiConfig from "../config/ApiConfig";
import UserContext from "../context/UserContext";
import { UsersApi } from "../openapi/generated/apis";

export const USER_ID = "USER_ID";
export const ACTIVE_ACCOUNT_ID = "ACTIVE_ACCOUNT_ID"

type UserProviderProps = {
  children: ReactNode;
};

function UserProvider({ children }: UserProviderProps) {
  const [userId, setUserId] = useState("");
  const [activeAccountId, setActiveAccountId] = useState("");

  // ローカルストレージに値がある場合はそれを使用
  useEffect(() => {
    const storedUserId = localStorage.getItem(USER_ID);
    if(storedUserId){
      setUserId(storedUserId)
    }

    const storedActiveAccountId = localStorage.getItem(ACTIVE_ACCOUNT_ID);
    if(storedActiveAccountId){
      setActiveAccountId(storedActiveAccountId)
    }
  }, [])

  // ログインでuserIdがセットされた際に
  useEffect(() => {
    if(userId){
      localStorage.setItem(USER_ID, userId)
      if(!activeAccountId){
        new UsersApi(apiConfig)
          .v1UsersUserIdGet({
            userId,
          })
          .then((res) => {
            const {accountId} = (res.include.filter((account) => account.isPrimary).pop()!)
            setActiveAccountId(accountId);
            localStorage.setItem(ACTIVE_ACCOUNT_ID, accountId)
          })
          .catch((e) => {
            console.log(e);
          });
        }
      }
  }, [userId]);

  return (
    <UserContext.Provider
      value={{ // eslint-disable-line react/jsx-no-constructed-context-values
        userId,
        setUserId,
        activeAccountId,
        setActiveAccountId,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}

export default UserProvider;
