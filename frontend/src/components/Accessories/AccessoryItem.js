import classes from "./AccessoryItem.module.css";
import { useContext } from "react";
import { Link } from "react-router-dom";
import CartContext from "../../store/cart-context";
import CartIcon from "../Cart/CartIcon";

const AccessoryItem = (props) => {
  let user = JSON.parse(localStorage.getItem("user-info"));

  const cartCtx = useContext(CartContext);

  const addToCartHandler = (amount) => {
    cartCtx.addItem({
      id: props.id,
      name: props.name,
      price: props.price,
      image: props.image,
    });

    const cartData = {
      username: user.username,
      accessory: props.id,
    };

    fetch("http://localhost:8080/api/carts/add/accessory", {
      method: "POST",
      body: JSON.stringify(cartData),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
  };

  return (
    <div className={classes.card}>
      <h2>{props.name}</h2>
      <img src={props.image} alt="AccessoryImage"></img>
      <p>
        <b>Manufacturer: </b>
        {props.manufacturer}
      </p>
      <p>
        <b>Country: </b>
        {props.country}
      </p>
      <h2 className={classes.price}>{props.price}$</h2>
      <Link to={`/accessories/${props.id}`} style={{ textDecoration: "none" }}>
        <button className={classes.button}>Details</button>
      </Link>
      {user !== null ? (
        <button className={classes.button} onClick={addToCartHandler}>
          <CartIcon />
          Add to Cart
        </button>
      ) : null}
    </div>
  );
};

export default AccessoryItem;
