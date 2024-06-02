import React from 'react';
import '../styles/paging-and-sorting.css';

function SortingOptions({ sortBy, setSortBy, sortOptions }) {
    const handleSortChange = (event) => {
        setSortBy(event.target.value);
    };

    return (
        <div className="sorting-container">
            <label htmlFor="sort-options" className="sorting-label">Řadit podle: </label>
            <select id="sort-options" value={sortBy} onChange={handleSortChange} className="sorting-select">
                {sortOptions.map(option => (
                    <option key={option.value} value={option.value}>{option.label}</option>
                ))}
            </select>
        </div>
    );
}

export default SortingOptions;