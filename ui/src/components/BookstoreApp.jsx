import './BookstoreApp.css';
import AuthProvider, {useAuth} from '../context/security/AuthContext';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './Navbar';

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
                        <Route path="/" />
                        <Route path="/cart" />
                        </Routes>
                </Router>
              </AuthProvider>
         </div>
        );
    }