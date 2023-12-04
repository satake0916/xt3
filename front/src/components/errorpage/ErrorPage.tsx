import "./ErrorPage.css"

import { isRouteErrorResponse, useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error = useRouteError();

  if (isRouteErrorResponse(error)) {
    if (error.status === 404) {
      return (
        <div className="error-page">
          <p>Hmm...this page doesnot exist.</p>
          <p>
            <i>{error.statusText || error.data.message}</i>
          </p>
        </div>
      );
    }
  } else if (error instanceof Error) {
    return (
      <div className="error-page">
        <h1>Oops!</h1>
        <p>Sorry, an unexpected error has occurred.</p>
        <p>
          <i>{error.name || error.message}</i>
        </p>
      </div>
    );
  }

  return (
    <div className="error-page">
      <p>You must not view this page. Whats happening...?</p>
    </div>
  );
  
}