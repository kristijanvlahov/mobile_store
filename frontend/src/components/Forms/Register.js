import { Fragment, useRef } from "react";
import classes from "./Register.module.css";

const Register = (props) => {
  const usernameInputRef = useRef();
  const passwordInputRef = useRef();
  const nameInputRef = useRef();
  const surnameInputRef = useRef();
  const emailInputRef = useRef();
  const addressInputRef = useRef();
  const roleInputRef = useRef();

  const submitHandler = (e) => {
    e.preventDefault();
    const enteredUsername = usernameInputRef.current.value;
    const enteredPassword = passwordInputRef.current.value;
    const enteredName = nameInputRef.current.value;
    const enteredSurname = surnameInputRef.current.value;
    const enteredEmail = emailInputRef.current.value;
    const enteredAddress = addressInputRef.current.value;
    const enteredRole = roleInputRef.current.value;

    const registerData = {
      username: enteredUsername,
      password: enteredPassword,
      name: enteredName,
      surname: enteredSurname,
      email: enteredEmail,
      address: enteredAddress,
      role: enteredRole,
    };
    props.onRegister(registerData);
  };

  return (
    <Fragment>
      <h1>Register</h1>
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
            <div className={classes.control}>
              <label htmlFor="name">Name:</label>
              <input type="text" required id="name" ref={nameInputRef}></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="surname">Surname:</label>
              <input
                type="text"
                required
                id="surname"
                ref={surnameInputRef}
              ></input>
            </div>
          </div>
          <div className={classes.row}>
            <div className={classes.control}>
              <label htmlFor="size">Email:</label>
              <input
                type="text"
                required
                id="email"
                ref={emailInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="address">Address:</label>
              <input
                type="text"
                required
                id="address"
                ref={addressInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="role">Role:</label>
              <select name="role" className="form-control" ref={roleInputRef}>
                <option id="1" value="ROLE_USER">
                  ROLE_USER
                </option>
                <option id="2" value="ROLE_ADMIN">
                  ROLE_ADMIN
                </option>
              </select>
            </div>
            <div className={classes.actions}>
              <button>Register</button>
            </div>
          </div>
        </div>
      </form>
    </Fragment>
  );
};

export default Register;
