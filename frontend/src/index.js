import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import Footer from './components/Layout/Footer';
import { FavoritesContextProvider } from './store/favourites-context';
import CartProvider from './store/CartProvider';

ReactDOM.render(
  <CartProvider>
  <FavoritesContextProvider>
  <BrowserRouter>
    <App />
    <Footer/>
    </BrowserRouter>
    </FavoritesContextProvider>
    </CartProvider>,
  document.getElementById('root')
);


