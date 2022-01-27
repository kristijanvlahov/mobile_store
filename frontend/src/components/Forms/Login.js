import { Fragment, useRef } from "react";
import classes from "./Login.module.css";

const Login = (props) => {
  const usernameInputRef = useRef();
  const passwordInputRef = useRef();

  const submitHandler = (e) => {
    e.preventDefault();
    const enteredUsername = usernameInputRef.current.value;
    const enteredPassword = passwordInputRef.current.value;

    const loginData = {
      username: enteredUsername,
      password: enteredPassword,
    };
    props.onLogin(loginData);
  };

  return (
    <Fragment>
      <h1>Login</h1>
      <form className={classes.form} onSubmit={submitHandler}>
        <div className={classes.container}>
          <div className={classes.row}>
            <div className={classes.control}>
              <label htmlFor="username">Username:</label>
              <input
                type="text"
                required
                id="username"
                ref={usernameInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="image">Password:</label>
              <input
                type="password"
                required
                id="password"
                ref={passwordInputRef}
              ></input>
            </div>
            <div className={classes.actions}>
              <button>Login</button>
            </div>
          </div>
        </div>
      </form>
    </Fragment>
  );
};

export default Login;
