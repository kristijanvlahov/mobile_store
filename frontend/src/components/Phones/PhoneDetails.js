import { Fragment, useContext } from "react";
import { useParams } from "react-router-dom";
import CartContext from "../../store/cart-context";
import CartIcon from "../Cart/CartIcon";
import Header from "../Layout/Header";
import classes from "./PhoneDetails.module.css";

const PhoneDetails = (props) => {
  const { id } = useParams();

  let user = JSON.parse(localStorage.getItem("user-info"));

  const phoneId = parseInt(id);

  const selectedPhone = props.phones.find((p) => p.id === phoneId);

  const cartCtx = useContext(CartContext);

  const addToCartHandler = () => {
    cartCtx.addItem({
      id: selectedPhone.id,
      name: selectedPhone.name,
      price: selectedPhone.price,
      image: selectedPhone.image,
    });

    const cartData = {
      username: user.username,
      phone: selectedPhone.id,
    };

    fetch("http://localhost:8080/api/carts/add", {
      method: "POST",
      body: JSON.stringify(cartData),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
  };

  return (
    <Fragment>
      <Header />
      <div className={classes.container}>
        <div className={classes.image}>
          <h1>{selectedPhone.name}</h1>
          <img src={selectedPhone.image} alt="PhoneImage"></img>
          <h1>{selectedPhone.price}$</h1>
        </div>
        <div className={classes.details}>
          <h2>Phone Details:</h2>
          <p>Weight: {selectedPhone.weight}g</p>
          <p>Size: {selectedPhone.size} inches</p>
          <p>Resolution: {selectedPhone.resolution}</p>
          <p>Operating System: {selectedPhone.os}</p>
          <p>CPU: {selectedPhone.cpu}</p>
          <p>Memory: {selectedPhone.memory}</p>
          <p>Camera: {selectedPhone.camera}</p>
          <p>Battery: {selectedPhone.battery}</p>
          {user !== null && (
            <button className={classes.button} onClick={addToCartHandler}>
              <CartIcon />
              Add to Cart
            </button>
          )}
        </div>
      </div>
    </Fragment>
  );
};

export default PhoneDetails;
