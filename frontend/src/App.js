import { useState, useEffect } from 'react';
import './App.css';
import { Routes, Route } from 'react-router-dom';
import PhoneDetails from './components/Phones/PhoneDetails';
import AccessoryList from './components/Accessories/AccessoryList';
import AccessoryDetails from './components/Accessories/AccessoryDetails';
import AddPhonePage from './pages/AddPhonePage';
import EditPhonePage from './pages/EditPhonePage';
import HomePage from './pages/HomePage';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import FavouritesPage from './pages/FavouritesPage';
import Cart from './components/Cart/Cart';

function App(props) {

  const [phones,setPhones] = useState([]);
  const [accessories,setAccessories] = useState([]);

  useEffect(()=>{
    const fetchPhones = async() => {
      const response = await fetch("http://localhost:8080/api/phones",{
        headers : { 
          'Content-Type': 'application/json',
          'Accept': 'application/json'
         }
        });
      const responseData = await response.json();
      setPhones(responseData);
      console.log(responseData);
    }
    fetchPhones();
  },[])

  useEffect(()=>{
    const fetchAccessories = async() => {
      const response = await fetch("http://localhost:8080/api/accessories",{
        headers : { 
          'Content-Type': 'application/json',
          'Accept': 'application/json'
         }
        });
      const responseData = await response.json();
      setAccessories(responseData);
      console.log(responseData);
    }
    fetchAccessories();
  },[])

  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<HomePage phones={phones} />}></Route>
        <Route path='/phones/:id' element={<PhoneDetails phones={phones}/>}></Route>
        <Route path='/accessories' element={<AccessoryList accessories={accessories} />}></Route>
        <Route path='/accessories/:id' element={<AccessoryDetails accessories={accessories} />}></Route>
        <Route path='/add-new' element={<AddPhonePage phones={phones}/>}></Route>
        <Route path='/edit/:id' element={<EditPhonePage phones={phones}/> }></Route>
        <Route path='/register' element={<RegisterPage /> }></Route>
        <Route path='/login' element={<LoginPage/> }></Route>
        <Route path='/favourites' element={<FavouritesPage/> }></Route>
        <Route path='/shopping-cart' element={<Cart/> }></Route>
      </Routes>
    </div>

  );
}

export default App;
