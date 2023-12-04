import {
  createContext,
  Dispatch,
  ReactNode,
  SetStateAction,
  useState,
} from "react";

export const UserContext = createContext(
  {} as {
    userId: number;
    setUserId: Dispatch<SetStateAction<number>>;
  },
);

type UserProviderProps = {
  children: ReactNode;
};

function UserProvider({ children }: UserProviderProps) {
  const [userId, setUserId] = useState(0);

  return (
    <UserContext.Provider
      value={{
        userId,
        setUserId,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}

export default UserProvider;
