import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import '../styles/search-bar.css';

function SearchBar() {
    let navigate = useNavigate();
    const [query, setQuery] = useState("");

    const handleInputChange = (e) => {
        setQuery(e.target.value);
    };

    const handleSearch = () => {
        navigate(`/search/${query}`)
    };

    return (
        <div className="search-bar-container">
            <input
                type="text"
                className="search-input"
                value={query}
                onChange={handleInputChange}
                placeholder="Hledat..."
            />
            <button className="search-button" onClick={handleSearch}>⌕</button>
        </div>
    );
}

export default SearchBar;