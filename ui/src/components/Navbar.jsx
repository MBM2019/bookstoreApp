import { Link } from 'react-router-dom';
import { ShoppingCart } from 'phosphor-react';
import { useAuth } from '../context/security/AuthContext'
import './Navbar.css';

export default function Navbar () {

   const authContext = useAuth()
   const username = authContext.username
   const isAuthenticated = authContext.isAuthenticated

   function logout() {
       authContext.logout()
   }
   return (
      <div className="navbar">
        <div className="links">
            {!isAuthenticated && <Link to="/login"> Log in </Link>}
            {!isAuthenticated && <Link to="/signin"> Sign in </Link>}
            {isAuthenticated && <Link to="/"> Shop </Link>}
            {isAuthenticated && <Link to="/logout"> Log out </Link>}
            {isAuthenticated && <Link to="/cart"><ShoppingCart size={32} /></Link>}
        </div>
      </div>
    );
};