import { Fragment, useContext } from "react";
import classes from "./FavouritesItem.module.css";
import FavoritesContext from "../../store/favourites-context";
import Like from "../../assets/like.svg";

const FavouritesItem = (props) => {
  let user = JSON.parse(localStorage.getItem("user-info"));

  const favoritesCtx = useContext(FavoritesContext);

  function toggleFavoriteStatusHandler() {
    favoritesCtx.removeFavorite(props.id);
    fetch("http://localhost:8080/api/favourites/delete/" + props.id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  return (
    <div className={classes.card}>
      <Fragment>
        <h2>{props.name}</h2>
        <span>
          {user !== null ? (
            <button
              className={classes.like}
              onClick={toggleFavoriteStatusHandler}
            >
              <img src={Like} alt="Like" className={classes.likeimage}></img>
              Unlike
            </button>
          ) : null}
        </span>
        <img src={props.image} alt="PhoneImage"></img>
        <h2 className={classes.price}>{props.price}$</h2>
      </Fragment>
    </div>
  );
};
export default FavouritesItem;
