import { Fragment, useState, useEffect, useContext } from "react";
import Header from "../Layout/Header";
import CartItem from "./CartItem";
import classes from "./Cart.module.css";
import { useNavigate } from "react-router-dom";
import CartContext from "../../store/cart-context";
import Stripe from "react-stripe-checkout";

const Cart = (props) => {
  const [phone, setPhone] = useState([]);
  const [accessory, setAccessory] = useState([]);

  const navigator = useNavigate();

  const cartCtx = useContext(CartContext);

  let user = JSON.parse(localStorage.getItem("user-info"));

  cartCtx.count = phone.length + accessory.length;

  useEffect(() => {
    const fetchPhone = async () => {
      const response = await fetch(
        "http://localhost:8080/api/carts/phones/" + user.username,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      );
      const responseData = await response.json();
      setPhone(responseData);
    };
    const fetchAccessories = async () => {
      const response = await fetch(
        "http://localhost:8080/api/carts/accessory/" + user.username,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        }
      );
      const responseData = await response.json();
      setAccessory(responseData);
    };
    fetchPhone();
    fetchAccessories();
  }, [user.username]);

  function deleteHandler(id) {
    const newData = phone.filter((item) => item.id !== id);
    setPhone(newData);
    cartCtx.removeItem(id);
  }

  function deleteAccessoryHandler(id) {
    const newData = accessory.filter((item) => item.id !== id);
    setAccessory(newData);
    cartCtx.removeItem(id);
  }

  async function handleToken(token) {
    await fetch("http://localhost:8080/api/payments/charge", {
      method: "POST",
      headers: {
        token: token.id,
        amount: total,
      },
    })
      .then(() => {
        alert("Payment Success");
      })
      .then(() => {
        cartCtx.count = 0;
        navigator("/");
      })
      .catch((error) => {
        alert(error);
      });
  }

  const hasItems = phone.length > 0 || accessory.length > 0;

  const getTotalCost = (productList, accessoryList) => {
    return (
      productList.reduce(
        (totalCost, { price: itemCost }) => totalCost + parseFloat(itemCost),
        0
      ) +
      accessoryList.reduce(
        (totalCost, { price: itemCost }) => totalCost + parseFloat(itemCost),
        0
      )
    );
  };

  const phoneItems = (
    <ul className={classes["cart-items"]}>
      {phone.map((item) => (
        <CartItem
          id={item.id}
          key={item.id}
          name={item.name}
          price={item.price}
          image={item.image}
          phone={phone}
          onDelete={deleteHandler}
        />
      ))}
    </ul>
  );

  const accessoryItems = (
    <ul className={classes["cart-items"]}>
      {accessory.map((item) => (
        <CartItem
          id={item.id}
          key={item.id}
          name={item.name}
          price={item.price}
          image={item.image}
          accessory={accessory}
          onDelete={deleteAccessoryHandler}
        />
      ))}
    </ul>
  );

  const total = getTotalCost(phone, accessory);

  return (
    <Fragment>
      <Header cartCount={phone.length} />
      {phoneItems}
      {accessoryItems}
      {!hasItems && <p>No items added in cart yet.</p>}
      {hasItems && (
        <div>
          <div className={classes.total}>
            <span>Total Amount</span>
            <span>${total.toFixed(2)}</span>
          </div>
          <Stripe
            stripeKey="pk_test_51KLqm3E6E3nBD8fSMDISion7wUwGFxJ2xVDw1dYWxYLuBaiGT4Jo2jwv3vWoa33uasJD9wb7mFlJNWV4chAVVL0o00rXoQq5uh"
            token={handleToken}
            amount={total * 100}
          ></Stripe>
        </div>
      )}
    </Fragment>
  );
};

export default Cart;
