import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js"; // Ensure Bootstrap JS is imported
import { Link } from "react-router-dom";

const AdminDefault = () => {
  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Admin Controller</h1>

      {/* Bootstrap Dropdown */}
      <div className="dropdown">
      <button
        className="btn btn-secondary dropdown-toggle"
        type="button"
        id="dropdownMenuButton"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      > Drop Down Management </button>
      

        <ul className="dropdown-menu" aria-labelledby="dropdownMenuLink">
          <li>
            <Link className="dropdown-item" to="/workflow"> Work Flow Management </Link>
          </li>
          <li>
            <Link className="dropdown-item" to="/field-management"> Field Management </Link>
          </li>
          <li>
            <Link className="dropdown-item" to="/template-management">Template</Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default AdminDefault;
