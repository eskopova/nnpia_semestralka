import React from 'react';
import '../styles/paging-and-sorting.css';

function PageSwitch({ page, setPage }) {
    return (
        <div className="paging-container">
            <button
                onClick={() => setPage(page - 1)}
                disabled={page <= 0}
                className="paging-button"
            >
                Předchozí
            </button>
            <span className="page-number">{page + 1}</span>
            <button
                onClick={() => setPage(page + 1)}
                className="paging-button"
            >
                Další
            </button>
        </div>
    );
}

export default PageSwitch;