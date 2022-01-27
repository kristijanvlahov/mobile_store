import classes from "./Search.module.css";

const Search = (props) => {
  async function searchHandler(searchInput) {
    let response = await fetch(
      "http://localhost:8080/api/phones/search/" + searchInput
    );
    response = await response.json();
    props.onSearch(response);
  }

  return (
    <input
      type="text"
      placeholder="Search phone"
      className={classes.input}
      onChange={(e) => searchHandler(e.target.value)}
    ></input>
  );
};

export default Search;
