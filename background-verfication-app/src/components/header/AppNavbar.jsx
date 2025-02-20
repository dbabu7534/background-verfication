import React from 'react';
import AdminNavbar from './AdminNavbar';
import UserNavbar from './UserNavbar';
import { Login } from '@mui/icons-material';

const AppNavbar = () => {
  // Get the role from localStorage
  const role = localStorage.getItem('role');

  console.log(role);
  

  // Render the appropriate Navbar based on the role
  if (role === 'ADMIN') {
    return <AdminNavbar />;
  } else if (role === 'USER') {
    return <UserNavbar />;
  } else {
    // Default Navbar or redirect to login if no role is found
    return <Login />;
  }
};

export default AppNavbar;