import { Fragment, useState } from "react";
import { useNavigate } from "react-router-dom";
import Login from "../components/Forms/Login";
import Header from "../components/Layout/Header";

const LoginPage = (props) => {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  async function loginHandler(loginData) {
    let response = await fetch("http://localhost:8080/api/login", {
      method: "POST",
      body: JSON.stringify(loginData),
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    });
    let data = await response.json();
    localStorage.setItem("user-info", JSON.stringify(data));
    setIsLoggedIn(true);
    navigate("/");
  }
  return (
    <Fragment>
      <Header isLoggedIn={isLoggedIn} />
      <Login onLogin={loginHandler} />
    </Fragment>
  );
};

export default LoginPage;
