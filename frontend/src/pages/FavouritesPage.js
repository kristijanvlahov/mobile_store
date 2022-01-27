import { Fragment } from "react";
import Header from "../components/Layout/Header";
import { useContext } from "react";
import FavoritesContext from "../store/favourites-context";
import FavouritesList from "../components/Phones/FavouritesList";

const FavouritesPage = (props) => {
  const favoritesCtx = useContext(FavoritesContext);

  let content;

  if (favoritesCtx.totalFavorites === 0) {
    content = <p>No favorites added yet.</p>;
  } else {
    content = <FavouritesList phones={favoritesCtx.favorites} />;
  }

  return (
    <Fragment>
      <Header />
      <h1>My Favourites</h1>
      {content}
    </Fragment>
  );
};

export default FavouritesPage;
