import { Fragment, useState } from "react";
import { useNavigate } from "react-router-dom";
import AddNewProduct from "../components/Forms/AddNewProduct";
import Header from "../components/Layout/Header";

const AddPhonePage = (props) => {

  const [phones, setPhones] = useState(props.phones);
  const navigate = useNavigate();
  const addPhoneHandler = (phoneData) => {
    fetch("http://localhost:8080/api/phones/add", {
      method: "POST",
      body: JSON.stringify(phoneData),
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      setPhones(phones);
      navigate("/");
    });
  };

  return (
    <Fragment>
    <Header />
    <AddNewProduct onAddPhone={addPhoneHandler} />
    </Fragment>
  );
 
};

export default AddPhonePage;
