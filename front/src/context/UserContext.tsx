import { createContext,Dispatch, SetStateAction } from "react";

const UserContext = createContext(
  {} as {
    userId: string;
    setUserId: Dispatch<SetStateAction<string>>;
    activeAccountId: string;
    setActiveAccountId: Dispatch<SetStateAction<string>>;
  },
);

export default UserContext;