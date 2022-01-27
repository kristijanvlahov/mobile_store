import classes from "./FavouritesList.module.css";
import FavouritesItem from "./FavouritesItem";

const FavouritesList = (props) => {
  return (
    <main className={classes.main}>
      <div className={classes.grid}>
        {props.phones.map((phone) => (
          <FavouritesItem
            id={phone.id}
            key={phone.id}
            name={phone.phoneName}
            image={phone.phoneImage}
            price={phone.phonePrice}
          />
        ))}
      </div>
    </main>
  );
};

export default FavouritesList;
