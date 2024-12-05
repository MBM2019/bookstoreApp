import './BookstoreApp.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Navbar } from './Navbar';

export default function BookstoreApp() {
    return (
        <div className="App">
          <Router>
              <Navbar />
              <Routes>
                  <Route path="/" />
                  <Route path="/cart" />
                  </Routes>
              </Router>
         </div>
        );
    }