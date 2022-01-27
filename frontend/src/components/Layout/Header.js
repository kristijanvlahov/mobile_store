import { Fragment, useContext } from "react";
import { NavLink } from "react-router-dom";
import classes from "./Header.module.css";
import image from "../../assets/store.jpg";
import CartContext from "../../store/cart-context";
import CartIcon from "../Cart/CartIcon";

const Header = (props) => {
  let user = JSON.parse(localStorage.getItem("user-info"));
  const logoutHandler = () => {
    localStorage.clear();
  };

  const cartCtx = useContext(CartContext);

  return (
    <Fragment>
      <header className={classes.header}>
        <h1>Mobile Store</h1>
        <nav className={classes.nav}>
          <ul>
            <li>
              <NavLink to="/">Phones</NavLink>
            </li>
            <li>
              <NavLink to="/accessories">Accessories</NavLink>
            </li>
            {user !== null ? (
              <li>
                <NavLink to="/favourites">My Favourites</NavLink>
              </li>
            ) : null}
            {user !== null ? (
              <li>
                <NavLink to="/shopping-cart">
                  <CartIcon />
                  Cart
                  <span className={classes.badge}>
                    {cartCtx.count === undefined
                      ? cartCtx.items.length
                      : cartCtx.count}
                  </span>
                </NavLink>
              </li>
            ) : null}
            {user !== null && user.role === "ROLE_ADMIN" ? (
              <li>
                <NavLink to="/add-new">Add New Phone</NavLink>
              </li>
            ) : null}
          </ul>
        </nav>
        {!user && (
          <nav className={classes.nav}>
            <ul>
              <li>
                <NavLink to="/login">Login</NavLink>
              </li>
              <li>
                <NavLink to="/register">Register</NavLink>
              </li>
            </ul>
          </nav>
        )}
        {user && (
          <nav className={classes.nav}>
            <ul>
              <li>{user.username}</li>
              <li>
                <NavLink to="/login" onClick={logoutHandler}>
                  Logout
                </NavLink>
              </li>
            </ul>
          </nav>
        )}
      </header>
      <div className={classes["main-image"]}>
        <img src={image} alt="Mobile Store"></img>
      </div>
    </Fragment>
  );
};

export default Header;
