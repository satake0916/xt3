import {
  ReactNode,
  useEffect,
  useState,
} from "react";

import apiConfig from "../config/ApiConfig";
import UserContext from "../context/UserContext";
import { UsersApi } from "../openapi/generated/apis";

export const USER_ID = "USER_ID";
export const ACTIVE_ACCOUNT_ID = "ACTIVE_ACCOUNT_ID";
export const ACTIVE_ACCOUNT_NAME = "ACTIVE_ACCOUNT_NAME";

type UserProviderProps = {
  children: ReactNode;
};

function UserProvider({ children }: UserProviderProps) {
  const [userId, setUserId] = useState("");
  const [activeAccountId, setActiveAccountId] = useState("");
  const [activeAccountName, setActiveAccountName] = useState("");

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

    const storedActiveAccountName = localStorage.getItem(ACTIVE_ACCOUNT_NAME);
    if(storedActiveAccountName){
      setActiveAccountName(storedActiveAccountName);
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
            const {accountId, accountName} = (res.include.filter((account) => account.isPrimary).pop()!);
            setActiveAccountId(accountId);
            localStorage.setItem(ACTIVE_ACCOUNT_ID, accountId);
            setActiveAccountName(accountName);
            localStorage.setItem(ACTIVE_ACCOUNT_NAME, accountName);
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
        activeAccountName,
        setActiveAccountName,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}

export default UserProvider;
