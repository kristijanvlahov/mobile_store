import { useContext } from "react";
import classes from "./PhoneItem.module.css";
import { Link } from "react-router-dom";
import { Fragment } from "react";
import FavoritesContext from "../../store/favourites-context";
import CartContext from "../../store/cart-context";
import CartIcon from "../Cart/CartIcon";
import Like from "../../assets/like.svg";
import Trash from "../../assets/trash.png";

const PhoneItem = (props) => {
  let user = JSON.parse(localStorage.getItem("user-info"));

  const favoritesCtx = useContext(FavoritesContext);
  const cartCtx = useContext(CartContext);

  const addToCartHandler = () => {
    cartCtx.addItem({
      id: props.id,
      name: props.name,
      price: props.price,
      image: props.image,
    });

    cartCtx.count = 0;

    const cartData = {
      username: user.username,
      phone: props.phone.id,
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

  const itemIsFavorite = favoritesCtx.itemIsFavorite(props.id);

  function toggleFavoriteStatusHandler() {
    if (itemIsFavorite) {
      favoritesCtx.removeFavorite(props.id);
      fetch("http://localhost:8080/api/favourites/delete/" + props.id, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      });
    } else {
      favoritesCtx.addFavorite({
        id: props.id,
        phone: props.phone.id,
        phoneName: props.phone.name,
        phoneImage: props.phone.image,
        phonePrice: props.phone.price,
        username: user.username,
      });

      const phoneData = {
        id: props.id,
        phone: props.phone.id,
        name: props.phone.name,
        image: props.phone.image,
        price: props.phone.price,
        username: user.username,
      };

      fetch("http://localhost:8080/api/favourites/add", {
        method: "POST",
        body: JSON.stringify(phoneData),
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
    }
  }

  async function deleteHandler(id) {
    fetch("http://localhost:8080/api/phones/delete/" + id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
    props.onDelete(id);
  }

  return (
    <div className={classes.card}>
      <h2>{props.name}</h2>
      {user !== null ? (
        <span>
          <button
            className={classes.like}
            onClick={toggleFavoriteStatusHandler}
          >
            <img src={Like} alt="Like" className={classes.likeimage}></img>
            {itemIsFavorite ? " Unlike" : " Like"}
          </button>
        </span>
      ) : null}
      <img src={props.image} alt="PhoneImage"></img>
      <p>
        <b>Manufacturer: </b>
        {props.manufacturer}
      </p>
      <p>
        <b>Country: </b>
        {props.country}
      </p>
      <h2 className={classes.price}>{props.price}$</h2>
      <Link to={`/phones/${props.id}`} style={{ textDecoration: "none" }}>
        <button className={classes.button}>Details</button>
      </Link>
      {user !== null ? (
        <button className={classes.button} onClick={addToCartHandler}>
          <CartIcon />
          Add to Cart
        </button>
      ) : null}
      {user !== null && user.role === "ROLE_ADMIN" ? (
        <Fragment>
          <button
            className={classes.delete}
            onClick={() => deleteHandler(props.id)}
          >
            <img
              src={Trash}
              alt="TrashImage"
              className={classes.trashimage}
            ></img>
            Delete
          </button>
          <Link to={`/edit/${props.id}`} style={{ textDecoration: "none" }}>
            <button className={classes.button}>Edit Details</button>
          </Link>
        </Fragment>
      ) : null}
    </div>
  );
};
export default PhoneItem;
