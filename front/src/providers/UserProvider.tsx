import {
  ReactNode,
  useEffect,
  useState,
} from "react";

import apiConfig from "../config/ApiConfig";
import UserContext from "../context/UserContext";
import { UsersApi } from "../openapi/generated/apis";

type UserProviderProps = {
  children: ReactNode;
};

function UserProvider({ children }: UserProviderProps) {
  const [userId, setUserId] = useState("");
  const [activeAccountId, setActiveAccountId] = useState("");

  useEffect(() => {
    new UsersApi(apiConfig)
      .v1UsersUserIdGet({
        userId,
      })
      .then((res) => {
        setUserId(res.data.userId);
        setActiveAccountId(
          res.include.filter((account) => account.isPrimary).pop()!.accountId,
        );
      })
      .catch((e) => {
        console.log(e);
      });
  }, [userId]);

  return (
    <UserContext.Provider
      value={{
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
