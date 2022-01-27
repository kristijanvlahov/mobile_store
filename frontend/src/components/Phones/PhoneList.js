import { Fragment, useEffect, useState } from "react";
import Header from "../Layout/Header";
import Search from "../Search/Search";
import PhoneItem from "./PhoneItem";
import classes from "./PhoneList.module.css";

const PhoneList = (props) => {
  const [phones, setPhones] = useState([]);

  useEffect(() => {
    const fetchPhones = async () => {
      const response = await fetch("http://localhost:8080/api/phones", {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const responseData = await response.json();
      setPhones(responseData);
    };
    fetchPhones();
  }, []);

  function deleteHandler(id) {
    const newData = phones.filter((item) => item.id !== id);
    setPhones(newData);
  }

  function searchHandler(response) {
    setPhones(response);
  }

  return (
    <Fragment>
      <Header />
      <Search onSearch={searchHandler} />
      {phones.length === 0 && <h3>No phones found...</h3>}
      {phones.length !== 0 && (
        <main className={classes.main}>
          <div className={classes.grid}>
            {phones.map((phone) => (
              <PhoneItem
                phone={phone}
                key={phone.id}
                id={phone.id}
                name={phone.name}
                image={phone.image}
                price={phone.price}
                manufacturer={phone.manufacturer.name}
                country={phone.manufacturer.country}
                onDelete={deleteHandler}
              />
            ))}
          </div>
        </main>
      )}
    </Fragment>
  );
};
export default PhoneList;
