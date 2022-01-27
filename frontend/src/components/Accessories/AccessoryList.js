import { Fragment } from "react";
import Header from "../Layout/Header";
import AccessoryItem from "./AccessoryItem";
import classes from "./AccessoryList.module.css";

const AccessoryList = (props) => {
  return (
    <Fragment>
      <Header />
      <main className={classes.main}>
        <div className={classes.grid}>
          {props.accessories.map((accessory) => (
            <AccessoryItem
              key={accessory.id}
              id={accessory.id}
              name={accessory.name}
              image={accessory.image}
              price={accessory.price}
              manufacturer={accessory.manufacturer.name}
              country={accessory.manufacturer.country}
            />
          ))}
        </div>
      </main>
    </Fragment>
  );
};
export default AccessoryList;
