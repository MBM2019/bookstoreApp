import './BookstoreApp.css';
import AuthProvider, {useAuth} from '../context/security/AuthContext';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './Navbar';
import LoginComponent from './LoginComponent';
import LogoutComponent from './LogoutComponent';
import SigninComponent from './SigninComponent';
import ShopComponent from './ShopComponent';

function AuthenticatedRoute({children}) {
    const authContext = useAuth()

    if (authContext.isAuthenticated)
        return children

    return <Navigate to="/"/>
}

export default function BookstoreApp() {
    return (
        <div className="App">
            <AuthProvider>
                <Router>
                    <Navbar />
                    <Routes>
                        <Route path='/' element={ <LoginComponent /> } />
                        <Route path='/login' element={ <LoginComponent /> } />
                        <Route path='/signin' element={ <SigninComponent /> } />
                        <Route path='/shop' element={
                            <AuthenticatedRoute>
                                <ShopComponent />
                            </AuthenticatedRoute>
                        } />
                        <Route path="/shop" />
                        <Route path="/cart" />
                        <Route path='/logout' element={
                            <AuthenticatedRoute>
                                <LogoutComponent />
                            </AuthenticatedRoute>
                        } />
                        </Routes>
                </Router>
              </AuthProvider>
         </div>
        );
    }