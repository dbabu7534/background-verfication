import React from "react";
import { AppBar, Toolbar, Button, Typography, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const role = localStorage.getItem('role');

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <AppBar position="static" sx={{ marginBottom: "20px", paddingX: "10px" }}>
      <Toolbar sx={{ display: "flex", justifyContent: "space-between" }}>
        {/* Left Side: Buttons */}
        
        
        <Box sx={{ display: "flex", gap: "10px" }}>
          
          

          <Button color="inherit" onClick={() => navigate("/")}>
            Home
          </Button>

          {role === "ADMIN" && (
            <Button color="inherit" onClick={() => navigate("/management")}>
                Management
            </Button>
          )}
                  
          {role === "ADMIN" && (
            <Button color="inherit" onClick={ () => navigate("/candidates") }> Candidates </Button>
          )}

                  



          {role === "USER" && (
            <Button color="inherit" onClick={() => navigate("/upload-details")}>
              Uploaded Details
            </Button>
          )}


        </Box>

        {/* Center: Application Name */}
        <Typography variant="h6" sx={{ flexGrow: 1, textAlign: "center" }}>
          ABC Company
        </Typography>

        {/* Right Side: Upload File & Logout */}
        <Box sx={{ display: "flex", gap: "10px" }}>
{/*          
          <Button color="inherit" onClick={() => navigate("/upload-file")}>
            Upload File
          </Button> */}

        {role === "ADMIN" && (
            <Button color="inherit" onClick={ () => navigate("/status-candidates") }> Status </Button>
          )}
        
          <Button color="inherit" onClick={() => navigate("/login")}> Login </Button>
         
          <Button color="inherit" onClick={handleLogout}> Logout </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
