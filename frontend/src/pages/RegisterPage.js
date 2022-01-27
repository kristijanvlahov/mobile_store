import { Fragment } from "react";
import { useNavigate } from "react-router-dom";
import Register from "../components/Forms/Register";
import Header from "../components/Layout/Header";

const RegisterPage = (props) => {
  const navigate = useNavigate();
  const registerHandler = (registerData) => {
    fetch("http://localhost:8080/api/users/add", {
      method: "POST",
      body: JSON.stringify(registerData),
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      navigate("/login");
    });
  };

  return (
    <Fragment>
      <Header />
      <Register onRegister={registerHandler} />
    </Fragment>
  );
};

export default RegisterPage;
