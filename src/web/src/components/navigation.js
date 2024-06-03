import React from 'react';
import { Link } from "react-router-dom";
import LoginBar from './login-bar.js';
import SearchBar from './search-bar.js';
import '../styles/navigation.css';

function Nav({ onLoginStatusChange }) {
    return (
        <nav className="nav-container">
            <div className="nav-top">
                <div className="nav-top-left"><a href="/" className="home-link"><h3>TopPečivo</h3></a></div>
                <div className="nav-top-mid"><SearchBar /></div>
                <div className="nav-top-right"><LoginBar onLoginStatusChange={onLoginStatusChange} /></div>
            </div>
            <div id="navigation" className="nav-links">
                <ul>
                    <li><Link to="/products">Všechny produkty</Link></li>
                    <li><Link to="/by-store">Podle obchodu</Link></li>
                    <li><Link to="/by-category">Podle kategorie</Link></li>
                </ul>
            </div>
        </nav>
    );
}

export default Nav;
