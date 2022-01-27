import { Fragment } from "react";
import { useRef, useState, useEffect } from "react";
import classes from "./AddNewProduct.module.css";

const AddNewProduct = (props) => {
  const [manufacturers, setManufacturers] = useState([]);
  const [categories, setCategories] = useState([]);

  const nameInputRef = useRef();
  const imageInputRef = useRef();
  const priceInputRef = useRef();
  const weightInputRef = useRef();
  const sizeInputRef = useRef();
  const resolutionInputRef = useRef();
  const osInputRef = useRef();
  const cpuInputRef = useRef();
  const memoryInputRef = useRef();
  const cameraInputRef = useRef();
  const batteryInputRef = useRef();
  const manufacturerInputRef = useRef();
  const categoryInputRef = useRef();

  useEffect(() => {
    const fetchManufacturers = async () => {
      const response = await fetch("http://localhost:8080/api/manufacturers", {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const responseData = await response.json();
      setManufacturers(responseData);
    };
    fetchManufacturers();
  }, []);

  useEffect(() => {
    const fetchCategories = async () => {
      const response = await fetch("http://localhost:8080/api/categories", {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
      });
      const responseData = await response.json();
      setCategories(responseData);
    };
    fetchCategories();
  }, []);

  const submitHandler = (event) => {
    event.preventDefault();
    const enteredName = nameInputRef.current.value;
    const enteredImage = imageInputRef.current.value;
    const enteredPrice = priceInputRef.current.value;
    const enteredWeight = weightInputRef.current.value;
    const enteredSize = sizeInputRef.current.value;
    const enteredResolution = resolutionInputRef.current.value;
    const enteredOS = osInputRef.current.value;
    const enteredCPU = cpuInputRef.current.value;
    const enteredMemory = memoryInputRef.current.value;
    const enteredCamera = cameraInputRef.current.value;
    const enteredBattery = batteryInputRef.current.value;
    const enteredManufacturer = manufacturerInputRef.current.value;
    const enteredCategory = categoryInputRef.current.value;

    const phoneData = {
      name: enteredName,
      image: enteredImage,
      price: enteredPrice,
      weight: enteredWeight,
      size: enteredSize,
      resolution: enteredResolution,
      os: enteredOS,
      cpu: enteredCPU,
      memory: enteredMemory,
      camera: enteredCamera,
      battery: enteredBattery,
      manufacturer: enteredManufacturer,
      category: enteredCategory,
    };
    props.onAddPhone(phoneData);
  };
  return (
    <Fragment>
      <h1>Add New Phone</h1>
      <form className={classes.form} onSubmit={submitHandler}>
        <div className={classes.container}>
          <div className={classes.row}>
            <div className={classes.control}>
              <label htmlFor="name">Name:</label>
              <input type="text" required id="name" ref={nameInputRef}></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="image">Image URL:</label>
              <input
                type="text"
                required
                id="image"
                ref={imageInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="price">Price:</label>
              <input
                type="number"
                required
                id="price"
                ref={priceInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="weight">Weight:</label>
              <input
                type="number"
                required
                id="weight"
                ref={weightInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="size">Size:</label>
              <input
                type="number"
                step="0.1"
                required
                id="size"
                ref={sizeInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="resolution">Resolution:</label>
              <input
                type="text"
                required
                id="resolution"
                ref={resolutionInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="os">Operating System:</label>
              <input type="text" required id="os" ref={osInputRef}></input>
            </div>
          </div>
          <div className={classes.row}>
            <div className={classes.control}>
              <label htmlFor="cpu">CPU:</label>
              <input type="text" required id="cpu" ref={cpuInputRef}></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="memory">Memory:</label>
              <input
                type="text"
                required
                id="memory"
                ref={memoryInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="camera">Camera:</label>
              <input
                type="text"
                required
                id="camera"
                ref={cameraInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="battery">Battery:</label>
              <input
                type="text"
                required
                id="battery"
                ref={batteryInputRef}
              ></input>
            </div>
            <div className={classes.control}>
              <label htmlFor="manufacturer">Manufacturer:</label>
              <select
                name="manufacturer"
                className="form-control"
                ref={manufacturerInputRef}
              >
                {manufacturers.map((m) => (
                  <option key={m.id} value={m.id}>
                    {m.name}
                  </option>
                ))}
              </select>
            </div>
            <div className={classes.control}>
              <label htmlFor="category">Category:</label>
              <select
                name="category"
                className="form-control"
                ref={categoryInputRef}
              >
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>
            </div>
            <div className={classes.actions}>
              <button>Add Phone</button>
            </div>
          </div>
        </div>
      </form>
    </Fragment>
  );
};

export default AddNewProduct;
