import React from 'react';
import { Link } from 'react-router-dom';

const AdminNavbar = () => {
  return (
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" Link="#">Admin Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <Link class="nav-link active" aria-current="page" to="/home">Home</Link>
                
                <Link class="nav-link" to="/candidates"> All Candidates </Link>
                <Link class="nav-link" to="/status"> Candidate Status </Link>

                
                <Link class="nav-link disabled" to="#" tabindex="-1" aria-disabled="true">Disabled</Link>
            </div>
            </div>
        </div>
        </nav>
  );
};

export default AdminNavbar;
