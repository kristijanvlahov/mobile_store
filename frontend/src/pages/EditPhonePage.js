import { Fragment } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EditProduct from "../components/Forms/EditProduct";
import Header from "../components/Layout/Header";

const EditPhonePage = (props) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const phoneId = parseInt(id);
  console.log(props.phones);

  const addPhoneHandler = (phoneData) => {
    console.log(phoneData);
    fetch("http://localhost:8080/api/phones/edit/" + phoneId, {
      method: "PUT",
      body: JSON.stringify(phoneData),
      headers: {
        "Content-Type": "application/json",
      },
    }).then(() => {
      navigate("/");
    });
  };

  return (
    <Fragment>
      <Header />
      <EditProduct onEditPhone={addPhoneHandler} phones={props.phones} />;
    </Fragment>
  );
};

export default EditPhonePage;
