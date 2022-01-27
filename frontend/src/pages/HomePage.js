import { Fragment } from "react";
import PhoneList from "../components/Phones/PhoneList";

const HomePage = (props) => {

    return(
        <Fragment>
        <PhoneList phones={props.phones} />
        </Fragment>
    );
}

export default HomePage;