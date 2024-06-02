import {useParams} from "react-router-dom";

function SearchProductsList() {
    const searchInput = useParams()
    return (
        <div>{searchInput.text}</div>
    );
}

export default SearchProductsList