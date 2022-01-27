import classes from "./CartItem.module.css";
import Trash from "../../assets/trash.png";

const CartItem = (props) => {
  const price = `$${props.price.toFixed(2)}`;

  let user = JSON.parse(localStorage.getItem("user-info"));

  async function deleteHandler(id) {
    fetch(
      "http://localhost:8080/api/carts/delete/" + user.username + "/" + id,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      }
    );
    props.onDelete(id);
  }

  async function deleteAccessoriesHandler(id) {
    fetch(
      "http://localhost:8080/api/carts/delete/accessory/" +
        user.username +
        "/" +
        id,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      }
    );
    props.onDelete(id);
  }

  return (
    <li className={classes["cart-item"]}>
      <div className={classes.summary}>
        <h2>{props.name}</h2>
        <img src={props.image} className={classes.image} alt="ItemImage"></img>
        <div>
          <span className={classes.price}>{price}</span>
        </div>
      </div>
      <div className={classes.actions}>
        {props.phone && (
          <button onClick={() => deleteHandler(props.id)}>
            <img
              src={Trash}
              className={classes.trashimage}
              alt="TrashImage"
            ></img>
            Remove from cart
          </button>
        )}
        {props.accessory && (
          <button onClick={() => deleteAccessoriesHandler(props.id)}>
            <img
              src={Trash}
              className={classes.trashimage}
              alt="TrashImage"
            ></img>
            Remove from cart
          </button>
        )}
      </div>
    </li>
  );
};

export default CartItem;
