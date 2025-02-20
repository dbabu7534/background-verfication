import React from 'react';
import { Link } from 'react-router-dom';

const UserNavbar = () => {
  return (
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"> User Navbar </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <Link class="nav-link active" aria-current="page" to="/home">Home</Link>
          <Link class="nav-link" to="/upload-details"> Uploaded Documents </Link>
          <Link class="nav-link" to=""> Status </Link>
          {/* <a class="nav-link" href="#">Pricing</a>
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a> */}
        </div>
      </div>
    </div>
  </nav>
  );
};

export default UserNavbar;