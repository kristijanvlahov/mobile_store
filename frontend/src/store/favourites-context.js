import { createContext, useState, useEffect } from "react";

const FavoritesContext = createContext({
  favorites: [],
  totalFavorites: 0,
  addFavorite: (favoritePhone) => {},
  removeFavorite: (phoneId) => {},
  itemIsFavorite: (phoneId) => {},
});

export function FavoritesContextProvider(props) {
  const [userFavorites, setUserFavorites] = useState([]);

  useEffect(() => {
    const fetchFavourites = async () => {
      const response = await fetch("http://localhost:8080/api/favourites", {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const responseData = await response.json();
      setUserFavorites(responseData);
      console.log(responseData);
    };
    fetchFavourites();
  }, []);

  function addFavoriteHandler(favoritePhone) {
    fetch("http://localhost:8080/api/favourites/add", {
      method: "POST",
      body: JSON.stringify(favoritePhone),
      headers: {
        "Content-Type": "application/json",
      },
    }).then(
      setUserFavorites((prevUserFavorites) => {
        return prevUserFavorites.concat(favoritePhone);
      })
    );
  }

  function removeFavoriteHandler(phoneId) {
    fetch("http://localhost:8080/api/favourites/delete/" + phoneId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    });
    setUserFavorites((prevUserFavorites) => {
      return prevUserFavorites.filter((phone) => phone.id !== phoneId);
    });
  }

  function itemIsFavoriteHandler(phoneId) {
    return userFavorites.some((phone) => phone.id === phoneId);
  }

  const context = {
    favorites: userFavorites,
    totalFavorites: userFavorites.length,
    addFavorite: addFavoriteHandler,
    removeFavorite: removeFavoriteHandler,
    itemIsFavorite: itemIsFavoriteHandler,
  };

  return (
    <FavoritesContext.Provider value={context}>
      {props.children}
    </FavoritesContext.Provider>
  );
}

export default FavoritesContext;
