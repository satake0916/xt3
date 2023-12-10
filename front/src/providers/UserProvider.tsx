import {
  createContext,
  Dispatch,
  ReactNode,
  SetStateAction,
  useEffect,
  useState,
} from "react";

import apiConfig from "../config/ApiConfig";
import { UsersApi } from "../openapi/generated/apis";

export const UserContext = createContext(
  {} as {
    userId: number;
    setUserId: Dispatch<SetStateAction<number>>;
    activeAccountId: number;
    setActiveAccountId: Dispatch<SetStateAction<number>>;
  },
);

type UserProviderProps = {
  children: ReactNode;
};

function UserProvider({ children }: UserProviderProps) {
  const [userId, setUserId] = useState(0);
  const [activeAccountId, setActiveAccountId] = useState(0);

  useEffect(() => {
    new UsersApi(apiConfig)
      .usersUserIdGet({
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
