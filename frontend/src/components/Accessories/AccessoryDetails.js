import { Fragment } from "react";
import { useParams } from "react-router-dom";
import Header from "../Layout/Header";
import classes from "./AccessoryDetails.module.css";
import CartIcon from "../Cart/CartIcon";

const AccessoryDetails = (props) => {
  const { id } = useParams();

  let user = JSON.parse(localStorage.getItem('user-info'));

  const accessoryId = parseInt(id);

  const selectedAccessory = props.accessories.find((a) => a.id === accessoryId);

  return (
    <Fragment>
      <Header />
      <div className={classes.container}>
        <div className={classes.image}>
          <h1>{selectedAccessory.name}</h1>
          <img src={selectedAccessory.image} alt="AccessoryImage"></img>
          <h1>{selectedAccessory.price}$</h1>
        </div>
        <div className={classes.details}>
          <h2>Details:</h2>
          <p>Manufacturer: {selectedAccessory.manufacturer.name}</p>
          <p>Country: {selectedAccessory.manufacturer.country}</p>
          {user !== null && <button className={classes.button}><CartIcon/>Add to Cart</button>}
        </div>
      </div>
    </Fragment>
  );
};

export default AccessoryDetails;
