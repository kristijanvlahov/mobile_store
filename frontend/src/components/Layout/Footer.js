import { Fragment } from 'react';
import classes from './Footer.module.css';

const Footer = (props) => {
  return (
    <Fragment>
      <header className={classes.footer}>
        <h6>Copyright @2022</h6>
        <h6>Made by Kristijan Vlahov</h6>
      </header>
    </Fragment>
  );
};

export default Footer;